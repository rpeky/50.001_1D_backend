package com.travelapp.api.activities.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesCreateDTO;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.service.ActivitiesServiceImpl;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiRequest;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import java.util.List;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {

    @Autowired
    @Qualifier("defaultModelMapper")
    ModelMapper defaultMapper;

    @Autowired
    ObjectMapper jsonConverter;

    @Autowired
    ActivitiesServiceImpl activitiesService;

    @PostMapping
    public ResponseEntity<ApiResponse<ActivitiesReadDTO>> createActivity
            (@RequestBody ApiRequest<ActivitiesCreateDTO> request) {
        ActivitiesCreateDTO activityToCreate = request.getData();
        ActivitiesReadDTO activityCreated =  activitiesService.createActivity(activityToCreate);
        ApiResponse<ActivitiesReadDTO> response = new ApiResponse<>(activityCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ActivitiesReadDTO>> updateActivity
            (@RequestBody ApiRequest<ActivitiesUpdateDTO> request) {
        ActivitiesUpdateDTO activitiesUpdateDTO = request.getData();
        ActivitiesReadDTO activityUpdated = activitiesService.updateActivity(activitiesUpdateDTO);
        ApiResponse<ActivitiesReadDTO> response = new ApiResponse<>(activityUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ApiResponse<ActivitiesReadDTO>> getAllActivity(@PathVariable Long activityId) {
        ActivitiesReadDTO activityToShow = activitiesService.getActivity(activityId);
        ApiResponse<ActivitiesReadDTO> response = new ApiResponse<>(activityToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/load/all")
    public ResponseEntity<ApiResponse<List<ActivitiesReadDTO>>> getAllActivity() {
        List<ActivitiesReadDTO> activityListToShow = activitiesService.getAllActivities();
        ApiResponse<List<ActivitiesReadDTO>> response = new ApiResponse<>(activityListToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userUid}/all")
    public ResponseEntity<ApiResponse<List<ActivitiesReadDTO>>> getAllUserActivity(@PathVariable String userUid) {
        List<ActivitiesReadDTO> activitiesToShow = activitiesService.getAllUserActivities(userUid);
        ApiResponse<List<ActivitiesReadDTO>> response = new ApiResponse<>(activitiesToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/user/{userUid}/{activityId}")
    public ResponseEntity<ApiResponse<Void>> deleteSpecificUserActivity (@PathVariable String userUid, @PathVariable Long activityId) {
        activitiesService.deleteUserActivity(userUid, activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
