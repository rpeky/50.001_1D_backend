package com.travelapp.api.globalnonsense.mappers.mymappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.activitymedia.DTO.MediaUpdateDTO;
import com.travelapp.api.activities.activitymedia.entity.Media;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.status.DTO.external.StatusUpdateDTO;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.status.repository.StatusRepository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

public class MyActivitiesUpdateMapper {

    public static Activities activityUpdateMapper(ObjectMapper jsonConverter, ActivitiesUpdateDTO updateDTO,
                                                  Activities activityToUpdate, StatusRepository statusRepository) {
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

        if (updateJson.has("medias")) {
            List<MediaUpdateDTO> mediasToUpdate = updateDTO.getMedias().orElse(null);
            // Clear the existing collection in-place:
            activityToUpdate.getMedias().clear();

            if (mediasToUpdate != null && !mediasToUpdate.isEmpty()) {
                for (MediaUpdateDTO mediaUpdateDTO : mediasToUpdate) {
                    Media media = new Media();
                    JsonNode mediaUpdateJson = jsonConverter.convertValue(mediaUpdateDTO, JsonNode.class);

                    if (mediaUpdateJson.has("type")) {
                        media.setType(mediaUpdateDTO.getType().orElse(null));
                    }
                    if (mediaUpdateJson.has("url")) {
                        media.setUrl(mediaUpdateDTO.getUrl().orElse(null));
                    }
                    // VERY IMPORTANT: Set the parent reference
                    media.setActivity(activityToUpdate);

                    activityToUpdate.getMedias().add(media);
                }
            }
        }

        if (updateJson.has("status")) {
            Status statusToUpdate = activityToUpdate.getStatus();
            StatusUpdateDTO statusUpdateDTO = updateDTO.getStatus().orElse(null);
            statusToUpdate.setStatusId(statusUpdateDTO.getStatusId().orElse(null));
            activityToUpdate.setStatus(statusToUpdate);
        }

        if (updateJson.has("status")) {

            StatusUpdateDTO statusUpdateDTO = updateDTO.getStatus().orElse(null);

            if (!statusUpdateDTO.getStatusId().isPresent()){
                throw new IllegalArgumentException("StatusID information required for update.");
            }

            Optional<Status> optionalStatus = statusRepository.findById(statusUpdateDTO.getStatusId().get());

            if (optionalStatus.isEmpty()) {
                throw new EntityNotFoundException("Status with ID " + statusUpdateDTO.getStatusId().get() + " not found");
            }

            Status statusToChangeTo = optionalStatus.get();
            activityToUpdate.setStatus(statusToChangeTo);
        }

        return activityToUpdate;
    }

}
