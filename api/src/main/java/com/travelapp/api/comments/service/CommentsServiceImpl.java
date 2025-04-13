package com.travelapp.api.comments.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.comments.repository.CommentsRepository;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itineraries.repository.ItinerariesRepository;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    ItinerariesRepository itinerariesRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    @Qualifier("strictModelMapper")
    ModelMapper strictMapper;

    //@Override
    public CommentsReadDTO createCommentActivity(CommentsCreateDTO commentsCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (commentsCreateDTO.getCreatedBy() != null) {
            String userUid = commentsCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(commentsCreateDTO.getCreatedBy().getUserUid());
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (commentsCreateDTO.getActivity() != null) {
                        Long activityId = commentsCreateDTO.getActivity().getActivityId();
                        if (activityId != null) {
                            Optional<Activities> optionalActivity = activitiesRepository.findById(commentsCreateDTO.getActivity().getActivityId());
                            if (optionalActivity.isPresent()) {
                                Activities activity = optionalActivity.get();
                                if (commentsCreateDTO.getComment() != null) {
                                    String comment = commentsCreateDTO.getComment();
                                    Comments commentToCreate = new Comments(createdBy, activity, comment);
                                    Comments commentCreated = commentsRepository.save(commentToCreate);
                                    return strictMapper.map(commentCreated, CommentsReadDTO.class);
                                } else {
                                    throw new IllegalArgumentException("Comment String information required for creation");
                                }
                            } else {
                                throw new EntityNotFoundException("Activity with ID " + activityId + " not found.");
                            }
                        } else {
                            throw new IllegalArgumentException("Activity ID information is required for creation.");
                        }
                    } else {
                        throw new IllegalArgumentException("Activity information is required for creation.");
                    }
                } else {
                    throw new EntityNotFoundException("User with UID " + userUid + " not found.");
                }
            } else {
                throw new IllegalArgumentException("User UID information is required for creation");
            }
        } else {
            throw new IllegalArgumentException("CreatedBy information is required for creation.");
        }
    }


    public CommentsReadDTO createCommentItinerary(CommentsCreateDTO commentsCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (commentsCreateDTO.getCreatedBy() != null) {
            String userUid = commentsCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(commentsCreateDTO.getCreatedBy().getUserUid());
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (commentsCreateDTO.getItinerary() != null) {
                        Long itineraryId = commentsCreateDTO.getItinerary().getItineraryId();
                        if (itineraryId != null) {
                            Optional<Itineraries> optionalItinerary = itinerariesRepository.findById(commentsCreateDTO.getItinerary().getItineraryId());
                            if (optionalItinerary.isPresent()) {
                                Itineraries itinerary = optionalItinerary.get();
                                if (commentsCreateDTO.getComment() != null) {
                                    String comment = commentsCreateDTO.getComment();
                                    Comments commentToCreate = new Comments(createdBy, itinerary, comment);
                                    Comments commentCreated = commentsRepository.save(commentToCreate);
                                    return strictMapper.map(commentCreated, CommentsReadDTO.class);
                                } else {
                                    throw new IllegalArgumentException("Comment String information required for creation");
                                }
                            } else {
                                throw new EntityNotFoundException("Itinerary with ID " + itineraryId + " not found.");
                            }
                        } else {
                            throw new IllegalArgumentException("Itinerary ID information is required for creation.");
                        }
                    } else {
                        throw new IllegalArgumentException("Itinerary information is required for creation.");
                    }
                } else {
                    throw new EntityNotFoundException("User with UID " + userUid + " not found.");
                }
            } else {
                throw new IllegalArgumentException("User UID information is required for creation");
            }
        } else {
            throw new IllegalArgumentException("CreatedBy information is required for creation.");
        }
    }

    @Transactional
    public void deleteComment(String userUid, Long commentId)
            throws EntityNotFoundException, IllegalArgumentException {
        if (userUid == null || commentId == null) {
            throw new IllegalArgumentException("User UID and comment ID must be provided.");
        }

        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found.");
        }

        Optional<Comments> commentOpt = commentsRepository.findById(commentId);
        if (commentOpt.isEmpty()) {
            throw new EntityNotFoundException("Comment with ID " + commentId + " not found.");
        }

        Comments comment = commentOpt.get();
        if (!comment.getCreatedBy().getUserUid().equals(userUid)) {
            throw new IllegalArgumentException("User is not the owner of this comment.");
        }

        commentsRepository.delete(comment);
    }




}
