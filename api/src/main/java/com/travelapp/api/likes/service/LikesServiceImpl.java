package com.travelapp.api.likes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.likes.DTO.LikesCreateDTO;
import com.travelapp.api.likes.DTO.LikesReadDTO;
import com.travelapp.api.likes.entity.Likes;
import com.travelapp.api.likes.repository.LikesRepository;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
// If supporting itineraries, import them:
// import com.travelapp.api.itineraries.entity.Itineraries;
// import com.travelapp.api.itineraries.repository.ItinerariesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ActivitiesRepository activitiesRepository;

    // Uncomment and autowire if supporting itineraries:
    // @Autowired
    // private ItinerariesRepository itinerariesRepository;

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public LikesReadDTO createLike(LikesCreateDTO createDTO) {
        Likes likeEntity = new Likes();
        // Set the user
        Long userId = createDTO.getUserId();
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        likeEntity.setUser(user);

        // Set the activity if provided
        if (createDTO.getActivityId() != null) {
            Long activityId = createDTO.getActivityId();
            Activities activity = activitiesRepository.findById(activityId)
                    .orElseThrow(() -> new EntityNotFoundException("Activity not found: " + activityId));
            likeEntity.setActivity(activity);
        } else {
            likeEntity.setActivity(null);
        }

        // Optionally, set the itinerary if provided.
        if (createDTO.getItineraryId() != null) {
            // Uncomment if supporting itineraries:
            // Long itineraryId = createDTO.getItineraryId();
            // Itineraries itinerary = itinerariesRepository.findById(itineraryId)
            //        .orElseThrow(() -> new EntityNotFoundException("Itinerary not found: " + itineraryId));
            // likeEntity.setItinerary(itinerary);
            likeEntity.setItinerary(null);
        } else {
            likeEntity.setItinerary(null);
        }

        Likes saved = likesRepository.save(likeEntity);
        return modelMapper.map(saved, LikesReadDTO.class);
    }

    @Override
    public LikesReadDTO getLike(Long likeId) {
        Likes like = likesRepository.findById(likeId)
                .orElseThrow(() -> new EntityNotFoundException("Like not found: " + likeId));
        return modelMapper.map(like, LikesReadDTO.class);
    }

    @Override
    public List<LikesReadDTO> getAllLikes() {
        return likesRepository.findAll().stream()
                .map(like -> modelMapper.map(like, LikesReadDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LikesReadDTO> getLikesByUser(Long userId) {
        return likesRepository.findByUserUserId(userId).stream()
                .map(like -> modelMapper.map(like, LikesReadDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LikesReadDTO> getLikesByActivity(Long activityId) {
        return likesRepository.findByActivityActivityId(activityId).stream()
                .map(like -> modelMapper.map(like, LikesReadDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public long countLikesByActivity(Long activityId) {
        // Use the repository method for counting likes by activity.
        return likesRepository.countByActivityActivityId(activityId);
    }

    // Uncomment if you later want to support itinerary count:
    /*
    @Override
    public long countLikesByItinerary(Long itineraryId) {
        return likesRepository.countByItineraryItineraryId(itineraryId);
    }
    */

    @Override
    public List<LikesReadDTO> getLikesByUserUuid(String userUid) {
        Users user = usersRepository.findByUserUid(userUid)
                .orElseThrow(() -> new EntityNotFoundException("User with UID " + userUid + " not found"));
        return getLikesByUser(user.getUserId());
    }

    @Override
    @Transactional
    public void deleteLike(Long likeId) {
        Likes like = likesRepository.findById(likeId)
                .orElseThrow(() -> new EntityNotFoundException("Like not found: " + likeId));
        likesRepository.delete(like);
    }
}

