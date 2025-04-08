package com.travelapp.api.users.service;

import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadDTO;
import com.travelapp.api.users.DTO.UsersUpdateDTO;

import java.util.List;

public interface UsersService {
    UsersReadDTO createUser(UsersCreateDTO usersDTO);
    UsersReadDTO updateUser(UsersUpdateDTO userDTO);
    UsersReadDTO getUser(String userUid);
    void deleteUser(String userUid);
    List<UsersReadDTO> getAllUsers();

}
