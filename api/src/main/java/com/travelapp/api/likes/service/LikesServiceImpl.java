package com.travelapp.api.likes.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.activities.service.ActivitiesServiceImpl;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itineraries.repository.ItinerariesRepository;
import com.travelapp.api.likes.DTO.LikesCreateDTO;
import com.travelapp.api.likes.DTO.LikesReadDTO;
import com.travelapp.api.likes.entity.Likes;
import com.travelapp.api.likes.repository.LikesRepository;
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
public class LikesServiceImpl implements LikesService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    ItinerariesRepository itinerariesRepository;

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    @Qualifier("defaultModelMapper")
    ModelMapper defaultMapper;


    @Override
    public LikesReadDTO createLikeActivity(LikesCreateDTO likesCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (likesCreateDTO.getCreatedBy() != null) {
            String userUid = likesCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(likesCreateDTO.getCreatedBy().getUserUid());
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (likesCreateDTO.getActivity() != null) {
                        Long activityId = likesCreateDTO.getActivity().getActivityId();
                        if (activityId != null) {
                            Optional<Activities> optionalActivity = activitiesRepository.findById(likesCreateDTO.getActivity().getActivityId());
                            if (optionalActivity.isPresent()) {
                                Activities activity = optionalActivity.get();
                                Likes likeToCreate = new Likes(createdBy,activity);
                                Likes likeCreated = likesRepository.save(likeToCreate);
                                return defaultMapper.map(likeCreated, LikesReadDTO.class);
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

    @Override
    public LikesReadDTO createLikeItinerary(LikesCreateDTO likesCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (likesCreateDTO.getCreatedBy() != null) {
            String userUid = likesCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(userUid);
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (likesCreateDTO.getItinerary() != null) {
                        Long itineraryId = likesCreateDTO.getItinerary().getItineraryId();
                        if (itineraryId != null) {
                            Optional<Itineraries> optionalItinerary = itinerariesRepository.findById(itineraryId);
                            if (optionalItinerary.isPresent()) {
                                Itineraries itinerary = optionalItinerary.get();
                                Likes likeToCreate = new Likes(createdBy, itinerary);
                                Likes likeCreated = likesRepository.save(likeToCreate);
                                return defaultMapper.map(likeCreated, LikesReadDTO.class);
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
    public void deleteLikeActivity(String userUid, Long activityId) {
        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found");
        }

        if (!activitiesRepository.existsByActivityId(activityId)) {
            throw new EntityNotFoundException("Activity with ID " + activityId + " not found");
        }

        Optional<Likes> likeOpt = likesRepository.findByCreatedBy_UserUidAndActivity_ActivityId(userUid, activityId);
        if (likeOpt.isPresent()) {
            likesRepository.delete(likeOpt.get());
        } else {
            throw new EntityNotFoundException(
                    String.format("Like not found for userUid: %s and activityId: %d", userUid, activityId)
            );
        }
    }

    @Transactional
    public void deleteLikeItinerary(String userUid, Long itineraryId) {
        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found");
        }

        if (!itinerariesRepository.existsByItineraryId(itineraryId)) {
            throw new EntityNotFoundException("Itinerary with ID " + itineraryId + " not found");
        }

        Optional<Likes> likeOpt = likesRepository.findByCreatedBy_UserUidAndItinerary_ItineraryId(userUid, itineraryId);
        if (likeOpt.isPresent()) {
            likesRepository.delete(likeOpt.get());
        } else {
            throw new EntityNotFoundException(
                    String.format("Like not found for userUid: %s and itineraryId: %d", userUid, itineraryId)
            );
        }
    }



    //for debugging purposes
    public LikesReadDTO getLike(Long likeId) {
        Optional<Likes> optionalLike = likesRepository.findById(likeId);
        if (optionalLike.isPresent()) {
            Likes like = optionalLike.get();
            return defaultMapper.map(like, LikesReadDTO.class);
        } else {
            throw new EntityNotFoundException("Like with ID: " + likeId + " not found");
        }
    }

    public LikesReadDTO getLikeByUidActivityId(String userUid, Long activityId) {
        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found");
        }
        Optional<Likes> optionalLike = likesRepository.findByCreatedBy_UserUidAndActivity_ActivityId(userUid, activityId);
        if (optionalLike.isPresent()) {
            Likes like = optionalLike.get();
            return defaultMapper.map(like, LikesReadDTO.class);
        } else {
            throw new EntityNotFoundException("Like for Activity ID " + activityId + " not found");
        }
    }

    public LikesReadDTO getLikeByUidItineraryId(String userUid, Long itineraryId) {
        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found");
        }
        Optional<Likes> optionalLike = likesRepository.findByCreatedBy_UserUidAndItinerary_ItineraryId(userUid, itineraryId);
        if (optionalLike.isPresent()) {
            Likes like = optionalLike.get();
            return defaultMapper.map(like, LikesReadDTO.class);
        } else {
            throw new EntityNotFoundException("Like for Itinerary ID " + itineraryId + " not found");
        }
    }



}
