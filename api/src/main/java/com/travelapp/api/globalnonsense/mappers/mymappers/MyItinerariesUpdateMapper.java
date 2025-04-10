package com.travelapp.api.globalnonsense.mappers.mymappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.itineraries.DTO.ItinerariesUpdateDTO;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itinerarydayactivity.daysactivity.DTO.DayActivityCUDTO;
import com.travelapp.api.itinerarydayactivity.daysactivity.entity.DayActivity;
import com.travelapp.api.itinerarydayactivity.itinerarydays.DTO.ItineraryDayCUDTO;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;
import com.travelapp.api.status.DTO.external.StatusUpdateDTO;
import com.travelapp.api.status.entity.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

public class MyItinerariesUpdateMapper {
    public static Itineraries itineraryUpdateMapper(ObjectMapper jsonConverter, ItinerariesUpdateDTO updateDTO,
                                                    Itineraries itineraryToUpdate,
                                                    ActivitiesRepository activitiesRepository) {
        JsonNode updateJson = jsonConverter.convertValue(updateDTO, JsonNode.class);

        if (updateJson.has("timeline")) {
            List<ItineraryDayCUDTO> timelineToCreate = updateDTO.getTimeline().orElse(null);

            // Work with the persistent collection reference rather than replacing it.
            List<ItineraryDay> persistentTimeline = itineraryToUpdate.getTimeline();
            persistentTimeline.clear();

            if (timelineToCreate != null && !timelineToCreate.isEmpty()) {
                for (ItineraryDayCUDTO itineraryDayCUDTO : timelineToCreate) {
                    ItineraryDay newItineraryDay = new ItineraryDay();
                    // Set the back-reference to the parent itinerary.
                    newItineraryDay.setItinerary(itineraryToUpdate);

                    if (itineraryDayCUDTO.getDay() != null) {
                        newItineraryDay.setDay(itineraryDayCUDTO.getDay());

                        List<DayActivityCUDTO> dayActivitiesToCreate = itineraryDayCUDTO.getActivities();
                        if (dayActivitiesToCreate != null && !dayActivitiesToCreate.isEmpty()) {
                            List<DayActivity> newDayActivities = new ArrayList<>();

                            for (DayActivityCUDTO dayActivityCUDTO : dayActivitiesToCreate) {
                                if (dayActivityCUDTO.getActivity() != null) {
                                    DayActivity newDayActivity = new DayActivity();
                                    // Set the parent reference for the DayActivity
                                    newDayActivity.setDay(newItineraryDay);

                                    // Map the activity reference
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
                        }
                        // Add the newly created ItineraryDay to the persistent timeline collection
                        persistentTimeline.add(newItineraryDay);
                    } else {
                        throw new IllegalArgumentException("Itinerary Day Number not provided");
                    }
                }
            }
        }


        if (updateJson.has("title")) {
            itineraryToUpdate.setTitle(updateDTO.getTitle().orElse(null));
        }

        if (updateJson.has("description")) {
            itineraryToUpdate.setDescription(updateDTO.getDescription().orElse(null));
        }

        if (updateJson.has("priceRange")) {
            itineraryToUpdate.setPriceRange(updateDTO.getPriceRange().orElse(null));
        }

        if (updateJson.has("thumbnail")) {
            itineraryToUpdate.setThumbnail(updateDTO.getThumbnail().orElse(null));
        }

        if (updateJson.has("coverPhoto")) {
            itineraryToUpdate.setCoverPhoto(updateDTO.getCoverPhoto().orElse(null));
        }

        if (updateJson.has("status")) {
            Status statusToUpdate = itineraryToUpdate.getStatus();
            StatusUpdateDTO statusUpdateDTO = updateDTO.getStatus().orElse(null);
            statusToUpdate.setStatusName(statusUpdateDTO.getStatusName().orElse(null));
            itineraryToUpdate.setStatus(statusToUpdate);
        }

        return itineraryToUpdate;
    }
}
