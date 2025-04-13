package com.travelapp.api.itineraries.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.comments.service.CommentsServiceImpl;
import com.travelapp.api.itineraries.DTO.ItinerariesCreateDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesReadDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesUpdateDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherCUDTO;
import com.travelapp.api.itineraries.service.ItinerariesServiceImpl;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiRequest;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiResponse;
import com.travelapp.api.likes.DTO.LikesCreateDTO;
import com.travelapp.api.likes.DTO.LikesReadDTO;
import com.travelapp.api.likes.service.LikesServiceImpl;
import com.travelapp.api.ratings.DTO.RatingsCreateDTO;
import com.travelapp.api.ratings.DTO.RatingsReadDTO;
import com.travelapp.api.ratings.service.RatingsServiceImpl;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;

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
    @Autowired
    LikesServiceImpl likesService;
    @Autowired
    CommentsServiceImpl commentsService;
    @Autowired
    RatingsServiceImpl ratingsService;


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

    @GetMapping("/load/all")
    public ResponseEntity<ApiResponse<List<ItinerariesReadDTO>>> getAllItineraries() {
        List<ItinerariesReadDTO> itineraryListToShow = itinerariesService.getAllItineraries();
        ApiResponse<List<ItinerariesReadDTO>> response = new ApiResponse<>(itineraryListToShow);
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


    //likes
    @PostMapping("/likes/{userUid}/{itineraryId}")
    public ResponseEntity<ApiResponse<LikesReadDTO>> createLike(@PathVariable String userUid, @PathVariable Long itineraryId) {
        LikesCreateDTO likesCreateDTO = new LikesCreateDTO();
        UsersOtherCreateDTO createdBy = new UsersOtherCreateDTO();
        ItinerariesOtherCUDTO itinerary = new ItinerariesOtherCUDTO();
        createdBy.setUserUid(userUid);
        itinerary.setItineraryId(itineraryId);

        likesCreateDTO.setActivity(null);
        likesCreateDTO.setCreatedBy(createdBy);
        likesCreateDTO.setItinerary(itinerary);

        LikesReadDTO likeCreated =  likesService.createLikeItinerary(likesCreateDTO);
        ApiResponse<LikesReadDTO> response = new ApiResponse<>(likeCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/likes/delete/{userUid}/{itineraryId}")
    public ResponseEntity<ApiResponse<Void>> deleteLike(@PathVariable String userUid, @PathVariable Long itineraryId) {
        likesService.deleteLikeItinerary(userUid, itineraryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/comments/create")
    public ResponseEntity<ApiResponse<CommentsReadDTO>> createComment(@RequestBody ApiRequest<CommentsCreateDTO> request) {
        CommentsCreateDTO commentsCreateDTO = request.getData();
        CommentsReadDTO commentCreated =  commentsService.createCommentItinerary(commentsCreateDTO);
        ApiResponse<CommentsReadDTO> response = new ApiResponse<>(commentCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/delete/{userUid}/{itineraryId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable String userUid, @PathVariable Long itineraryId) {
        commentsService.deleteComment(userUid, itineraryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/ratings/create")
    public ResponseEntity<ApiResponse<RatingsReadDTO>> createRating(@RequestBody ApiRequest<RatingsCreateDTO> request) {
        RatingsCreateDTO ratingsCreateDTO = request.getData();
        RatingsReadDTO ratingCreated = ratingsService.createRatingItinerary(ratingsCreateDTO);
        ApiResponse<RatingsReadDTO> response = new ApiResponse<>(ratingCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/ratings/delete/{userUid}/{ratingId}")
    public ResponseEntity<ApiResponse<Void>> deleteRating(@PathVariable String userUid, @PathVariable Long ratingId) {
        ratingsService.deleteRating(userUid, ratingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
