package com.travelapp.api.itineraries.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesCreateDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesReadDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesUpdateDTO;
import com.travelapp.api.itineraries.service.ItinerariesServiceImpl;
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
@RequestMapping("/itineraries")
public class ItinerariesController {

    @Autowired
    @Qualifier("defaultModelMapper")
    ModelMapper defaultMapper;

    @Autowired
    ObjectMapper jsonConverter;

    @Autowired
    ItinerariesServiceImpl itinerariesService;


    @PostMapping
    public ResponseEntity<ApiResponse<ItinerariesReadDTO>> createItinerary
            (@RequestBody ApiRequest<ItinerariesCreateDTO> request) {
        ItinerariesCreateDTO itineraryToCreate = request.getData();
        ItinerariesReadDTO itineraryCreated =  itinerariesService.createItinerary(itineraryToCreate);
        ApiResponse<ItinerariesReadDTO> response = new ApiResponse<>(itineraryCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ItinerariesReadDTO>> updateItinerary
            (@RequestBody ApiRequest<ItinerariesUpdateDTO> request) {
        ItinerariesUpdateDTO itinerariesUpdateDTO = request.getData();
        ItinerariesReadDTO itineraryUpdated = itinerariesService.updateItinerary(itinerariesUpdateDTO);
        ApiResponse<ItinerariesReadDTO> response = new ApiResponse<>(itineraryUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{itineraryId}")
    public ResponseEntity<ApiResponse<ItinerariesReadDTO>> getItinerary(@PathVariable Long itineraryId) {
        ItinerariesReadDTO itineraryToShow = itinerariesService.getItinerary(itineraryId);
        ApiResponse<ItinerariesReadDTO> response = new ApiResponse<>(itineraryToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userUid}/all")
    public ResponseEntity<ApiResponse<List<ItinerariesReadDTO>>> getAllUserItinerary(@PathVariable String userUid) {
        List<ItinerariesReadDTO> itinerariesToShow = itinerariesService.getAllUserItineraries(userUid);
        ApiResponse<List<ItinerariesReadDTO>> response = new ApiResponse<>(itinerariesToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userUid}/{itineraryId}")
    public ResponseEntity<ApiResponse<Void>> deleteSpecificUserItinerary (@PathVariable String userUid, @PathVariable Long itineraryId) {
        itinerariesService.deleteUserItinerary(userUid, itineraryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
