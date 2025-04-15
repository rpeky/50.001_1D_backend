package com.travelapp.api.activities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesCreateDTO;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.globalnonsense.mappers.mymappers.MyActivitiesUpdateMapper;
import com.travelapp.api.ratings.RatingsCalculator.RatingsCalculator;
import com.travelapp.api.ratings.entity.Ratings;
import com.travelapp.api.ratings.repository.RatingsRepository;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.users.userfollows.repository.UserFollowsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserFollowsRepository userFollowsRepository;
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    @Qualifier("strictModelMapper")
    private ModelMapper strictMapper;


    @Autowired
    private ObjectMapper jsonConverter;

    @Override
    public ActivitiesReadDTO createActivity(ActivitiesCreateDTO activitiesCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {

        Activities activityToCreate = defaultMapper.map(activitiesCreateDTO, Activities.class);

        // Set default status if none is provided
        if (activityToCreate.getStatus() == null) {
            throw new IllegalArgumentException("Status information is required for creation");
        }

        UsersOtherCreateDTO usersOtherCreateDTO = activitiesCreateDTO.getCreatedBy();

        if (usersOtherCreateDTO != null && usersOtherCreateDTO.getUserUid() != null) {

            Optional<Users> optionalActivityUser = usersRepository.findByUserUid(usersOtherCreateDTO.getUserUid());

            if (optionalActivityUser.isPresent()) {
                Users activityUser = optionalActivityUser.get();
                activityToCreate.setCreatedBy(activityUser);
                Activities activityCreated = activitiesRepository.save(activityToCreate);
                return strictMapper.map(activityCreated, ActivitiesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("User with UID: "
                        + usersOtherCreateDTO.getUserUid() + " not found.");
            }
        }
        else {
            throw new IllegalArgumentException("CreatedBy information is required for creation");
        }
    }

    @Override
    public ActivitiesReadDTO updateActivity(ActivitiesUpdateDTO activitiesUpdateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (activitiesUpdateDTO.getActivityId().isPresent()
                && activitiesUpdateDTO.getActivityId().orElse(null) != null) {

            Long activityToUpdateId = activitiesUpdateDTO.getActivityId().get();
            Optional<Activities> optionalActivityToUpdate = activitiesRepository.findById(activityToUpdateId);

            if (optionalActivityToUpdate.isPresent()) {
                Activities activityToUpdate =  MyActivitiesUpdateMapper
                        .activityUpdateMapper(jsonConverter, activitiesUpdateDTO, optionalActivityToUpdate.get());
                Activities activityUpdated = activitiesRepository.save(activityToUpdate);
                return strictMapper.map(activityUpdated, ActivitiesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("Activity with ID: "
                        + activityToUpdateId + " not found.");
            }

        } else {
            throw new IllegalArgumentException("ActivityID is required for update");
        }

    }

    @Override
    public ActivitiesReadDTO getActivity(Long activityId)
            throws EntityNotFoundException {
        Optional<Activities> optionalExistingActivity =  activitiesRepository.findById(activityId);
        if (optionalExistingActivity.isPresent()) {
            Activities activityRetrieved = optionalExistingActivity.get();

            ActivitiesReadDTO activityToShow = strictMapper.map(activityRetrieved, ActivitiesReadDTO.class);

            List<Ratings> allRatingsList = ratingsRepository.findAllWhereItineraryIsNull();

            activityToShow.setRatings(RatingsCalculator
                    .computeBayesianAverageActItin(activityRetrieved.getRatingsList(), allRatingsList, 10));

            return activityToShow;

        } else {
            throw new EntityNotFoundException("Activity with ID: "
                    + activityId + " not found.");
        }
    }

    @Override
    public List<ActivitiesReadDTO> getAllUserActivities(String userUid)
            throws EntityNotFoundException {
        if (usersRepository.existsByUserUid(userUid)) {
            List<Activities> userActivities = activitiesRepository.findByCreatedBy_UserUid(userUid);
            List<ActivitiesReadDTO> userActivitiesToShow = new ArrayList<>();
            for (Activities activity : userActivities) {
                ActivitiesReadDTO activityToShow = strictMapper.map(activity, ActivitiesReadDTO.class);

                activityToShow.setRatings(RatingsCalculator.computeAverageRating(activity.getRatingsList()));

                userActivitiesToShow.add(activityToShow);
            }

            userActivitiesToShow.sort((a1, a2) -> a1.getCreatedAt().compareTo(a2.getCreatedAt()));

            return userActivitiesToShow;
        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }

    }

    public List<ActivitiesReadDTO> getAllActivities()
            throws EntityNotFoundException {
        List<Activities> allActivitiesList =  activitiesRepository.findAll();
        List<ActivitiesReadDTO> listToReturn = new ArrayList<>();
        for (Activities activity : allActivitiesList){
            ActivitiesReadDTO activityToAdd = strictMapper.map(activity, ActivitiesReadDTO.class);

            activityToAdd.setRatings(RatingsCalculator.computeAverageRating(activity.getRatingsList()));

            listToReturn.add(activityToAdd);
        }

        listToReturn.sort((a1, a2) -> a2.getCreatedAt().compareTo(a1.getCreatedAt()));

        return listToReturn;
    }

    public List<ActivitiesReadDTO> getFollowingActivitiesList(String userUid)
        throws EntityNotFoundException {
        Optional<Users> optionalUser = usersRepository.findByUserUid(userUid);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            List<Activities> allFollowingActivities = GetUserFollowingActivities
                    .followingActivities(userFollowsRepository, activitiesRepository, user, strictMapper);

            List<ActivitiesReadDTO> listToReturn = new ArrayList<>();
            for (Activities activity : allFollowingActivities){
                ActivitiesReadDTO activityToAdd = strictMapper.map(activity, ActivitiesReadDTO.class);

                activityToAdd.setRatings(RatingsCalculator.computeAverageRating(activity.getRatingsList()));

                listToReturn.add(activityToAdd);
            }
            return listToReturn;

        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }


    @Transactional
    @Override
    public void deleteUserActivity(String userUid, Long activityId) throws EntityNotFoundException {
        if (usersRepository.existsByUserUid(userUid)) {
                Optional<Activities> optionalActivityToDelete =  activitiesRepository.findById(activityId);
            if (optionalActivityToDelete.isPresent()){
                Activities activityToDelete = optionalActivityToDelete.get();
                if (activityToDelete.getCreatedBy().getUserUid().equals(userUid)){
                    activitiesRepository.deleteById(activityId);
                } else {
                    throw new IllegalArgumentException("Activity does not belong to User");
                }
            } else {
                throw new EntityNotFoundException("Activity with ID: "
                        + activityId + " not found.");
            }
        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }


}



