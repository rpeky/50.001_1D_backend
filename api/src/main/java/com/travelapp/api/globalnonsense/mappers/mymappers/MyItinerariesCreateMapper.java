package com.travelapp.api.globalnonsense.mappers.mymappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.itineraries.DTO.ItinerariesCreateDTO;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itinerarydayactivity.daysactivity.DTO.DayActivityCUDTO;
import com.travelapp.api.itinerarydayactivity.daysactivity.entity.DayActivity;
import com.travelapp.api.itinerarydayactivity.itinerarydays.DTO.ItineraryDayCUDTO;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;
import com.travelapp.api.status.DTO.external.StatusCreateDTO;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.users.DTO.other.UserOtherCreateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

public class MyItinerariesCreateMapper {
    public static Itineraries itineraryCreateMapper(ItinerariesCreateDTO createDTO,
                                                    Itineraries itineraryToCreate, ActivitiesRepository activitiesRepository,
                                                    UsersRepository usersRepository) {
        if (createDTO.getTitle() != null) {
            itineraryToCreate.setTitle(createDTO.getTitle());
        } else {
            throw new IllegalArgumentException("CreatedBy information is required for creation");
        }

        if (createDTO.getCreatedBy() != null) {
            UserOtherCreateDTO userOtherCreateDTO = createDTO.getCreatedBy();

            if (userOtherCreateDTO != null && userOtherCreateDTO.getUserUid() != null) {

                Optional<Users> optionalItineraryUser = usersRepository.findByUserUid(userOtherCreateDTO.getUserUid());

                if (optionalItineraryUser.isPresent()) {
                    Users itineraryUser = optionalItineraryUser.get();
                    itineraryToCreate.setCreatedBy(itineraryUser);
                } else {
                    throw new EntityNotFoundException("User with UID: "
                            + userOtherCreateDTO.getUserUid() + " not found.");
                }
            }
        } else {
            throw new IllegalArgumentException("CreatedBy information is required for creation");
        }

        itineraryToCreate.setDescription(createDTO.getDescription());
        itineraryToCreate.setPriceRange(createDTO.getPriceRange());


        if (createDTO.getTimeline() != null) {
            List<ItineraryDayCUDTO> timelineToCreate = createDTO.getTimeline();

            itineraryToCreate.getTimeline().clear();

            if (timelineToCreate != null && timelineToCreate.size() != 0) {

                List<ItineraryDay> newTimeline = new ArrayList<>();

                for (ItineraryDayCUDTO itineraryDayCUDTO : timelineToCreate) {

                    ItineraryDay newItineraryDay = new ItineraryDay();

                    newItineraryDay.setItinerary(itineraryToCreate);

                    if (itineraryDayCUDTO.getDay() != null) {

                        newItineraryDay.setDay(itineraryDayCUDTO.getDay());

                        List<DayActivityCUDTO> dayActivitiesToCreate = itineraryDayCUDTO.getActivities();

                        if (dayActivitiesToCreate != null || dayActivitiesToCreate.size() != 0) {

                            List<DayActivity> newDayActivities = new ArrayList<>();

                            for (DayActivityCUDTO dayActivityCUDTO : dayActivitiesToCreate) {

                                if (dayActivityCUDTO.getActivity() != null) {

                                    DayActivity newDayActivity = new DayActivity();

                                    newDayActivity.setDay(newItineraryDay);

                                    ActivitiesOtherCUDTO dayActivityToCreate = dayActivityCUDTO.getActivity();

                                    if (dayActivityToCreate.getActivityId() != null) {

                                        Long dayActivityId = dayActivityToCreate.getActivityId();
                                        Optional<Activities> optionalActivity = activitiesRepository.findById(dayActivityId);

                                        if (optionalActivity.isPresent()) {

                                            Activities activity = optionalActivity.get();

                                            newDayActivity.setActivity(activity);

                                            newDayActivities.add(newDayActivity);

                                        } else {
                                            throw new EntityNotFoundException("Activity with ID: " + dayActivityId + " not found.");
                                        }

                                    } else {
                                        throw new IllegalArgumentException("ActivityId is required to link activity");
                                    }

                                } else {
                                    throw new IllegalArgumentException("Activity Information is required");
                                }

                            }

                            newItineraryDay.setActivities(newDayActivities);

                            newTimeline.add(newItineraryDay);
                        }

                    } else {
                        throw new IllegalArgumentException("Itinerary Day Number not provided");
                    }

                }
                itineraryToCreate.setTimeline(newTimeline);
            }
        }

        itineraryToCreate.setThumbnail(createDTO.getThumbnail());
        itineraryToCreate.setCoverPhoto(createDTO.getCoverPhoto());

        if (createDTO.getStatus() != null) {
            Status statusToCreate = new Status();
            StatusCreateDTO statusCreateDTO = createDTO.getStatus();
            if (statusCreateDTO.getStatusName() != null) {
                statusToCreate.setStatusName(statusCreateDTO.getStatusName());
                itineraryToCreate.setStatus(statusToCreate);
            } else {
                throw new IllegalArgumentException("StatusName is required for creation");
            }
        } else {
            throw new IllegalArgumentException("Status information is required for creation");
        }

        return itineraryToCreate;
    }
}
