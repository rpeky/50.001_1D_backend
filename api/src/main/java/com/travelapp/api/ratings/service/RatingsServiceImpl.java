package com.travelapp.api.ratings.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itineraries.repository.ItinerariesRepository;
import com.travelapp.api.ratings.DTO.RatingsCreateDTO;
import com.travelapp.api.ratings.DTO.RatingsReadDTO;
import com.travelapp.api.ratings.entity.Ratings;
import com.travelapp.api.ratings.repository.RatingsRepository;
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
public class RatingsServiceImpl {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    ItinerariesRepository itinerariesRepository;

    @Autowired
    RatingsRepository ratingsRepository;

    @Autowired
    @Qualifier("strictModelMapper")
    ModelMapper strictMapper;

    public RatingsReadDTO createRatingActivity(RatingsCreateDTO ratingsCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (ratingsCreateDTO.getCreatedBy() != null) {
            String userUid = ratingsCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(userUid);
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (ratingsCreateDTO.getActivity() != null) {
                        Long activityId = ratingsCreateDTO.getActivity().getActivityId();
                        if (activityId != null) {
                            Optional<Activities> optionalActivity = activitiesRepository.findById(activityId);
                            if (optionalActivity.isPresent()) {
                                Activities activity = optionalActivity.get();
                                if (ratingsCreateDTO.getRating() != null) {
                                    Long rating = ratingsCreateDTO.getRating();
                                    if (rating < 1 || rating > 5) {
                                        throw new IllegalArgumentException("Rating must be between 1 and 5");
                                    }
                                    Ratings ratingToCreate = new Ratings(createdBy, activity, rating);
                                    Ratings ratingCreated = ratingsRepository.save(ratingToCreate);
                                    return strictMapper.map(ratingCreated, RatingsReadDTO.class);
                                } else {
                                    throw new IllegalArgumentException("Rating value is required for creation");
                                }
                            } else {
                                throw new EntityNotFoundException("Activity with ID " + activityId + " not found.");
                            }
                        } else {
                            throw new IllegalArgumentException("Activity ID is required for rating creation.");
                        }
                    } else {
                        throw new IllegalArgumentException("Activity information is required for creation.");
                    }
                } else {
                    throw new EntityNotFoundException("User with UID " + userUid + " not found.");
                }
            } else {
                throw new IllegalArgumentException("User UID is required for creation");
            }
        } else {
            throw new IllegalArgumentException("CreatedBy information is required for creation.");
        }
    }

    public RatingsReadDTO createRatingItinerary(RatingsCreateDTO ratingsCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (ratingsCreateDTO.getCreatedBy() != null) {
            String userUid = ratingsCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(userUid);
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (ratingsCreateDTO.getItinerary() != null) {
                        Long itineraryId = ratingsCreateDTO.getItinerary().getItineraryId();
                        if (itineraryId != null) {
                            Optional<Itineraries> optionalItinerary = itinerariesRepository.findById(itineraryId);
                            if (optionalItinerary.isPresent()) {
                                Itineraries itinerary = optionalItinerary.get();
                                if (ratingsCreateDTO.getRating() != null) {
                                    Long rating = ratingsCreateDTO.getRating();
                                    if (rating < 1 || rating > 5) {
                                        throw new IllegalArgumentException("Rating must be between 1 and 5");
                                    }
                                    Ratings ratingToCreate = new Ratings(createdBy, itinerary, rating);
                                    Ratings ratingCreated = ratingsRepository.save(ratingToCreate);
                                    return strictMapper.map(ratingCreated, RatingsReadDTO.class);
                                } else {
                                    throw new IllegalArgumentException("Rating value is required for creation");
                                }
                            } else {
                                throw new EntityNotFoundException("Itinerary with ID " + itineraryId + " not found.");
                            }
                        } else {
                            throw new IllegalArgumentException("Itinerary ID is required for rating creation.");
                        }
                    } else {
                        throw new IllegalArgumentException("Itinerary information is required for creation.");
                    }
                } else {
                    throw new EntityNotFoundException("User with UID " + userUid + " not found.");
                }
            } else {
                throw new IllegalArgumentException("User UID is required for creation");
            }
        } else {
            throw new IllegalArgumentException("CreatedBy information is required for creation.");
        }
    }

    @Transactional
    public void deleteRating(String userUid, Long ratingId)
            throws EntityNotFoundException, IllegalArgumentException {
        if (userUid == null || ratingId == null) {
            throw new IllegalArgumentException("User UID and rating ID must be provided.");
        }

        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found.");
        }

        Optional<Ratings> ratingOpt = ratingsRepository.findById(ratingId);
        if (ratingOpt.isEmpty()) {
            throw new EntityNotFoundException("Rating with ID " + ratingId + " not found.");
        }

        Ratings rating = ratingOpt.get();
        if (!rating.getCreatedBy().getUserUid().equals(userUid)) {
            throw new IllegalArgumentException("User is not the owner of this rating.");
        }

        ratingsRepository.delete(rating);
    }
}
