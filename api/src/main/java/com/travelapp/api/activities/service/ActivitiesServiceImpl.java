package com.travelapp.api.activities.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesCreateDTO;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.mappers.mymappers.MyActivitiesUpdateMapper;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.users.DTO.UserActivityCreateDTO;
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
            Status activeStatus = new Status();
            activeStatus.setStatusName("ACTIVE");
            // Maintain the bi-directional link between activity and status
            activeStatus.setActivity(activityToCreate);
            activityToCreate.setStatus(activeStatus);
        }

        UserActivityCreateDTO userActivityCreateDTO = activitiesCreateDTO.getCreatedBy();

        if (userActivityCreateDTO != null && userActivityCreateDTO.getUserUid() != null) {

            Optional<Users> optionalActivityUser = usersRepository.findByUserUid(userActivityCreateDTO.getUserUid());

            if (optionalActivityUser.isPresent()) {
                Users activityUser = optionalActivityUser.get();
                activityToCreate.setCreatedBy(activityUser);
                Activities activityCreated = activitiesRepository.save(activityToCreate);
                return defaultMapper.map(activityCreated, ActivitiesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("User with UID: "
                        + userActivityCreateDTO.getUserUid() + " not found.");
            }
        }
        else {
            throw new IllegalArgumentException("CreatedBy information is required for update");
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

    public ActivitiesReadDTO getUserActivity(String userUid, Long activityId)
            throws EntityNotFoundException {
        if (usersRepository.existsByUserUid(userUid)) {
            Optional<Activities> optionalUserActivity =
                    activitiesRepository.findByCreatedBy_UserUidAndActivityId(userUid, activityId);
            if (optionalUserActivity.isPresent()) {
                return defaultMapper.map(optionalUserActivity.get(), ActivitiesReadDTO.class);
            } else {
                throw new EntityNotFoundException("Activity with ID: "
                        + activityId + " not found.");
            }
        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

    @Transactional
    public void deleteActivity(Long activityId) throws EntityNotFoundException {
        if (activitiesRepository.existsById(activityId)) {
            activitiesRepository.deleteById(activityId);
        } else {
            throw new EntityNotFoundException("Activity with ID: "
                    + activityId + " not found.");
        }
    }

    @Transactional
    public void deleteUserActivity(String userUid, Long activityId) throws EntityNotFoundException {
        if (usersRepository.existsByUserUid(userUid)) {
            if (activitiesRepository.existsById(activityId)) {
                activitiesRepository.deleteByCreatedBy_UserUidAndActivityId(userUid, activityId);
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



