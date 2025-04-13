package com.travelapp.api.activities.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.activities.DTO.ActivitiesCreateDTO;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;
import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.activities.service.ActivitiesServiceImpl;
import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.comments.service.CommentsServiceImpl;
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
@RequestMapping("/activities")
public class ActivitiesController {

    @Autowired
    @Qualifier("defaultModelMapper")
    ModelMapper defaultMapper;

    @Autowired
    ObjectMapper jsonConverter;

    @Autowired
    ActivitiesServiceImpl activitiesService;
    @Autowired
    LikesServiceImpl likesService;
    @Autowired
    CommentsServiceImpl commentsService;
    @Autowired
    RatingsServiceImpl ratingsService;

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
    public ResponseEntity<ApiResponse<ActivitiesReadDTO>> getUserActivity(@PathVariable Long activityId) {
        ActivitiesReadDTO activityToShow = activitiesService.getActivity(activityId);
        ApiResponse<ActivitiesReadDTO> response = new ApiResponse<>(activityToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/load/all")
    public ResponseEntity<ApiResponse<List<ActivitiesReadDTO>>> getAllFollowingActivity() {
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

    @GetMapping("/user/{userUid}/following")
    public ResponseEntity<ApiResponse<List<ActivitiesReadDTO>>> getAllFollowingActivity(@PathVariable String userUid) {
        List<ActivitiesReadDTO> activitiesToShow = activitiesService.getFollowingActivitiesList(userUid);
        ApiResponse<List<ActivitiesReadDTO>> response = new ApiResponse<>(activitiesToShow);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/user/{userUid}/{activityId}")
    public ResponseEntity<ApiResponse<Void>> deleteSpecificUserActivity (@PathVariable String userUid, @PathVariable Long activityId) {
        activitiesService.deleteUserActivity(userUid, activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //likes
    @PostMapping("/likes/{userUid}/{activityId}")
    public ResponseEntity<ApiResponse<LikesReadDTO>> createLike(@PathVariable String userUid, @PathVariable Long activityId) {
        LikesCreateDTO likesCreateDTO = new LikesCreateDTO();
        UsersOtherCreateDTO createdBy = new UsersOtherCreateDTO();
        ActivitiesOtherCUDTO activity = new ActivitiesOtherCUDTO();
        createdBy.setUserUid(userUid);
        activity.setActivityId(activityId);

        likesCreateDTO.setActivity(activity);
        likesCreateDTO.setCreatedBy(createdBy);
        likesCreateDTO.setItinerary(null);

        LikesReadDTO likeCreated =  likesService.createLikeActivity(likesCreateDTO);
        ApiResponse<LikesReadDTO> response = new ApiResponse<>(likeCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/likes/delete/{userUid}/{activityId}")
    public ResponseEntity<ApiResponse<Void>> deleteLike(@PathVariable String userUid, @PathVariable Long activityId) {
        likesService.deleteLikeActivity(userUid, activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/comments/create")
    public ResponseEntity<ApiResponse<CommentsReadDTO>> createComment(@RequestBody ApiRequest<CommentsCreateDTO> request) {
        CommentsCreateDTO commentsCreateDTO = request.getData();
        CommentsReadDTO commentCreated =  commentsService.createCommentActivity(commentsCreateDTO);
        ApiResponse<CommentsReadDTO> response = new ApiResponse<>(commentCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/delete/{userUid}/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable String userUid, @PathVariable Long commentId) {
        commentsService.deleteComment(userUid, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/ratings/create")
    public ResponseEntity<ApiResponse<RatingsReadDTO>> createRating(@RequestBody ApiRequest<RatingsCreateDTO> request) {
        RatingsCreateDTO ratingsCreateDTO = request.getData();
        RatingsReadDTO ratingCreated = ratingsService.createRatingActivity(ratingsCreateDTO);
        ApiResponse<RatingsReadDTO> response = new ApiResponse<>(ratingCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/ratings/delete/{userUid}/{ratingId}")
    public ResponseEntity<ApiResponse<Void>> deleteRating(@PathVariable String userUid, @PathVariable Long ratingId) {
        ratingsService.deleteRating(userUid, ratingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
