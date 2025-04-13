package com.travelapp.api.users.controller;

import com.travelapp.api.bookmarks.DTO.BookmarksCreateDTO;
import com.travelapp.api.bookmarks.DTO.BookmarksReadDTO;
import com.travelapp.api.bookmarks.service.BookmarksServiceImpl;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiRequest;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiResponse;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadElseDTO;
import com.travelapp.api.users.DTO.UsersReadSelfDTO;
import com.travelapp.api.users.DTO.UsersUpdateDTO;
import com.travelapp.api.users.service.UsersServiceImpl;
import com.travelapp.api.users.userfollows.DTO.UserFollowsCreateDTO;
import com.travelapp.api.users.userfollows.DTO.UserFollowsReadDTO;
import com.travelapp.api.users.userfollows.service.UserFollowsService;
import com.travelapp.api.users.usersettings.DTO.UserSettingsReadDTO;
import com.travelapp.api.users.usersettings.DTO.UserSettingsUpdateDTO;
import com.travelapp.api.users.usersettings.service.UserSettingsServiceImpl;

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
    @Autowired
    private UserFollowsService userFollowsService;
    @Autowired
    private BookmarksServiceImpl bookmarksService;
    @Autowired
    private UserSettingsServiceImpl userSettingsService;

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

    @PostMapping("/follow")
    public ResponseEntity<ApiResponse<UserFollowsReadDTO>> followUser(@RequestBody ApiRequest<UserFollowsCreateDTO> request) {
        UserFollowsCreateDTO userFollowsCreateDTO = request.getData();
        UserFollowsReadDTO followCreated = userFollowsService.createFollow(userFollowsCreateDTO);
        ApiResponse<UserFollowsReadDTO> response = new ApiResponse<>(followCreated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/follow/delete/{userUid}/{followId}")
    public ResponseEntity<ApiResponse<Void>> unfollow(@PathVariable String followerId, @PathVariable Long followId) {
        userFollowsService.deleteUserFollow(followerId, followId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/bookmarks")
    public ResponseEntity<ApiResponse<BookmarksReadDTO>> createBookmark(@RequestBody ApiRequest<BookmarksCreateDTO> request) {
        BookmarksCreateDTO bookmarksCreateDTO = request.getData();
        BookmarksReadDTO bookmarkCreated = bookmarksService.createBookmark(bookmarksCreateDTO);
        ApiResponse<BookmarksReadDTO> response = new ApiResponse<>(bookmarkCreated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/bookmarks/delete/{userUid}/{bookmarkId}")
    public ResponseEntity<ApiResponse<Void>> deleteBookmark(@PathVariable String userUid, @PathVariable Long bookmarkId) {
        bookmarksService.deleteBookmark(userUid, bookmarkId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/settings/{userUid}")
    public ResponseEntity<ApiResponse<UserSettingsReadDTO>> getSettings(@PathVariable String userUid) {
        UserSettingsReadDTO userSettingsReadDTO = userSettingsService.getUserSettings(userUid);
        ApiResponse<UserSettingsReadDTO> response = new ApiResponse<>(userSettingsReadDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/settings")
    public ResponseEntity<ApiResponse<UserSettingsReadDTO>> updateSettings(@RequestBody ApiRequest<UserSettingsUpdateDTO> request) {
        UserSettingsUpdateDTO updateDTO = request.getData();
        UserSettingsReadDTO userSettingsReadDTO = userSettingsService.updateUserSettings(updateDTO);
        ApiResponse<UserSettingsReadDTO> response = new ApiResponse<>(userSettingsReadDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
