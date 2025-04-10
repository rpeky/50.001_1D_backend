package com.travelapp.api.comments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.comments.DTO.CommentsUpdateDTO;
import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.comments.repository.CommentsRepository;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ActivitiesRepository activitiesRepository;

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    private ObjectMapper jsonConverter;

    @Override
    @Transactional
    public CommentsReadDTO createComment(CommentsCreateDTO createDTO) {
        Comments comment = new Comments();
        comment.setComment(createDTO.getComment());
        // Lookup user by ID
        Long userId = createDTO.getUserId();
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        comment.setCreatedBy(user);
        // Lookup activity if provided
        if (createDTO.getActivityId() != null) {
            Long activityId = createDTO.getActivityId();
            Activities activity = activitiesRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found: " + activityId));
            comment.setActivity(activity);
        }
        // maybe can add for itinerary?
        Comments saved = commentsRepository.save(comment);
        return defaultMapper.map(saved, CommentsReadDTO.class);
    }

    @Override
    @Transactional
    public CommentsReadDTO updateComment(CommentsUpdateDTO updateDTO) {
        if (updateDTO.getCommentId().isPresent() && updateDTO.getCommentId().orElse(null) != null) {
            Long commentId = updateDTO.getCommentId().get();
            Comments existing = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found: " + commentId));
            if (updateDTO.getComment().isPresent()) {
                existing.setComment(updateDTO.getComment().get());
            }
            if (updateDTO.getActivityId().isPresent()) {
                Long activityId = updateDTO.getActivityId().get();
                Activities activity = activitiesRepository.findById(activityId)
                    .orElseThrow(() -> new EntityNotFoundException("Activity not found: " + activityId));
                existing.setActivity(activity);
            }
            if (updateDTO.getItineraryId().isPresent()) {
                // Implement itinerary lookup similarly if needed
            }
            if (updateDTO.getUserId().isPresent()) {
                Long newUserId = updateDTO.getUserId().get();
                Users newUser = usersRepository.findById(newUserId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + newUserId));
                existing.setCreatedBy(newUser);
            }
            Comments updated = commentsRepository.save(existing);
            return defaultMapper.map(updated, CommentsReadDTO.class);
        } else {
            throw new IllegalArgumentException("Comment ID is required for update");
        }
    }

    @Override
    public CommentsReadDTO getComment(Long commentId) {
        Comments comment = commentsRepository.findById(commentId)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found: " + commentId));
        return defaultMapper.map(comment, CommentsReadDTO.class);
    }

    @Override
    public List<CommentsReadDTO> getAllComments() {
        return commentsRepository.findAll().stream()
            .map(c -> defaultMapper.map(c, CommentsReadDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<CommentsReadDTO> getCommentsByUser(Long userId) {
        return commentsRepository.findByCreatedByUserId(userId).stream()
            .map(c -> defaultMapper.map(c, CommentsReadDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<CommentsReadDTO> getCommentsByUserUuid(String userUid) {
        Users user = usersRepository.findByUserUid(userUid)
            .orElseThrow(() -> new EntityNotFoundException("User with UID " + userUid + " not found"));
        return getCommentsByUser(user.getUserId());
    }


    @Override
    public List<CommentsReadDTO> getCommentsByActivity(Long activityId) {
        return commentsRepository.findByActivityActivityId(activityId).stream()
            .map(c -> defaultMapper.map(c, CommentsReadDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<CommentsReadDTO> getCommentsByItinerary(Long itineraryId) {
        return commentsRepository.findByItineraryItineraryId(itineraryId).stream()
            .map(c -> defaultMapper.map(c, CommentsReadDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public long countCommentsByActivity(Long activityId) {
        return commentsRepository.countByActivityActivityId(activityId);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Comments comment = commentsRepository.findById(commentId)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found: " + commentId));
        commentsRepository.delete(comment);
    }
}

