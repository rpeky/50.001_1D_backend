package com.travelapp.api.mappers.mymappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.entity.Activities;

public class MyActivitiesUpdateMapper {

    public static Activities activityUpdateMapper(ObjectMapper jsonConverter, ActivitiesUpdateDTO updateDTO,
                                                  Activities activityToUpdate) {
        JsonNode updateJson = jsonConverter.convertValue(updateDTO, JsonNode.class);

        if (updateJson.has("title")) {
            activityToUpdate.setTitle(updateDTO.getTitle().orElse(null));
        }

        if (updateJson.has("description")) {
            activityToUpdate.setDescription(updateDTO.getDescription().orElse(null));
        }

        if (updateJson.has("location")) {
            activityToUpdate.setLocation(updateDTO.getLocation().orElse(null));
        }

        if (updateJson.has("locationLink")) {
            activityToUpdate.setLocationLink(updateDTO.getLocationLink().orElse(null));
        }

        if (updateJson.has("price")) {
            activityToUpdate.setPrice(updateDTO.getPrice().orElse(null));
        }

        if (updateJson.has("thumbnail")) {
            activityToUpdate.setThumbnail(updateDTO.getThumbnail().orElse(null));
        }

        return activityToUpdate;
    }

}
