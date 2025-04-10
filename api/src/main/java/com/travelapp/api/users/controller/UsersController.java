package com.travelapp.api.users.controller;

import com.travelapp.api.globalnonsense.responserequestwrappers.ApiRequest;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiResponse;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadElseDTO;
import com.travelapp.api.users.DTO.UsersReadSelfDTO;
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

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/me/{userUid}")
    public ResponseEntity<ApiResponse<UsersReadSelfDTO>> getUserSelfByUid(@PathVariable String userUid) {
        UsersReadSelfDTO userToShow = usersService.getUserSelf(userUid);
        ApiResponse<UsersReadSelfDTO> response = new ApiResponse<>(userToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userUid}")
    public ResponseEntity<ApiResponse<UsersReadElseDTO>> getUserOtherByUid(@PathVariable String userUid) {
        UsersReadElseDTO userToShow = usersService.getUserOther(userUid);
        ApiResponse<UsersReadElseDTO> response = new ApiResponse<>(userToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UsersReadSelfDTO>> createUser(@RequestBody ApiRequest<UsersCreateDTO> request) {
        UsersReadSelfDTO userCreated = usersService.createUser(request.getData());
        ApiResponse<UsersReadSelfDTO> response = new ApiResponse<>(userCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UsersReadSelfDTO>> updateUser(@RequestBody ApiRequest<UsersUpdateDTO> request) {
        UsersReadSelfDTO updatedUser = usersService.updateUser(request.getData());
        ApiResponse<UsersReadSelfDTO> response = new ApiResponse<>(updatedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{userUid}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userUid) {
        usersService.deleteUser(userUid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
