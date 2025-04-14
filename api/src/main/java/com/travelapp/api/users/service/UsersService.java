package com.travelapp.api.users.service;

import com.travelapp.api.AAsecurity.DTO.UsersLoginConfirmationDTO;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadSelfDTO;
import com.travelapp.api.users.DTO.UsersUpdateDTO;

import java.util.List;

public interface UsersService {
    void createUser(UsersCreateDTO usersDTO);
    UsersReadSelfDTO updateUser(UsersUpdateDTO userDTO);
    UsersReadSelfDTO getUserSelf(String userUid);
    void deleteUser(String userUid);
    List<UsersReadSelfDTO> getAllUsers();

}
