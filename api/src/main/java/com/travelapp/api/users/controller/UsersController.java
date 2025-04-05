package com.travelapp.api.users.controller;

import com.travelapp.api.responserequestwrappers.ApiRequest;
import com.travelapp.api.responserequestwrappers.ApiResponse;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadDTO;
import com.travelapp.api.users.DTO.UsersUpdateDTO;
import com.travelapp.api.users.service.UsersServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/{userUid}")
    public ResponseEntity<ApiResponse<UsersReadDTO>> getUserByUid(@PathVariable String userUid) {
        UsersReadDTO userToShow = usersService.getUser(userUid);
        ApiResponse<UsersReadDTO> response = new ApiResponse<>(userToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsersReadDTO>> createUser(@RequestBody ApiRequest<UsersCreateDTO> request) {
        UsersReadDTO userCreated = usersService.createUser(request.getData());
        ApiResponse<UsersReadDTO> response = new ApiResponse<>(userCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UsersReadDTO>> updateUser(@RequestBody ApiRequest<UsersUpdateDTO> request) {
        UsersReadDTO updatedUser = usersService.updateUser(request.getData());
        ApiResponse<UsersReadDTO> response = new ApiResponse<>(updatedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userUid}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userUid) {
        usersService.deleteUser(userUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
