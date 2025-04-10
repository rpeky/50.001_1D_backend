package com.travelapp.api.activities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesCreateDTO;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.globalnonsense.mappers.mymappers.MyActivitiesUpdateMapper;
import com.travelapp.api.users.DTO.other.UserOtherCreateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

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
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;


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

        UserOtherCreateDTO userOtherCreateDTO = activitiesCreateDTO.getCreatedBy();

        if (userOtherCreateDTO != null && userOtherCreateDTO.getUserUid() != null) {

            Optional<Users> optionalActivityUser = usersRepository.findByUserUid(userOtherCreateDTO.getUserUid());

            if (optionalActivityUser.isPresent()) {
                Users activityUser = optionalActivityUser.get();
                activityToCreate.setCreatedBy(activityUser);
                Activities activityCreated = activitiesRepository.save(activityToCreate);
                return defaultMapper.map(activityCreated, ActivitiesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("User with UID: "
                        + userOtherCreateDTO.getUserUid() + " not found.");
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
                return defaultMapper.map(activityUpdated, ActivitiesReadDTO.class);
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
            Activities activityToShow = optionalExistingActivity.get();
            return defaultMapper.map(activityToShow, ActivitiesReadDTO.class);
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
                ActivitiesReadDTO activityToShow = defaultMapper.map(activity, ActivitiesReadDTO.class);
                userActivitiesToShow.add(activityToShow);
            }
            return userActivitiesToShow;
        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }

    }

    public List<ActivitiesReadDTO> getAllActivities()
            throws EntityNotFoundException {
        List<Activities> allActivitiesList =  activitiesRepository.findAll();
        List<ActivitiesReadDTO> lisToReturn = new ArrayList<>();
        for (Activities activity : allActivitiesList){
            ActivitiesReadDTO activityToAdd = defaultMapper.map(activity, ActivitiesReadDTO.class);
            lisToReturn.add(activityToAdd);
        }
        return lisToReturn;
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



