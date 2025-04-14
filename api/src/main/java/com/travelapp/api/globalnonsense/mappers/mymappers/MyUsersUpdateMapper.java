package com.travelapp.api.globalnonsense.mappers.mymappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.users.DTO.UsersUpdateDTO;
import com.travelapp.api.users.entity.Users;

public class MyUsersUpdateMapper {

    public static Users usersUpdateMapper(ObjectMapper jsonConverter, UsersUpdateDTO updateDTO,
                                                  Users userToUpdate){
        JsonNode updateJson = jsonConverter.convertValue(updateDTO, JsonNode.class);

        if (updateJson.has("userUid")) {
            userToUpdate.setUserUid(updateDTO.getUserUid().orElse(null));
        }

        if (updateJson.has("userName")) {
            userToUpdate.setUserName(updateDTO.getUserName().orElse(null));
        }

        if (updateJson.has("email")) {
            userToUpdate.setEmail(updateDTO.getEmail().orElse(null));
        }

        if (updateJson.has("bio")) {
            userToUpdate.setBio(updateDTO.getBio().orElse(null));
        }

        return userToUpdate;
    }

}
