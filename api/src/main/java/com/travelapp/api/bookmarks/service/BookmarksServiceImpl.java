package com.travelapp.api.bookmarks.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.bookmarks.DTO.BookmarksCreateDTO;
import com.travelapp.api.bookmarks.DTO.BookmarksReadDTO;
import com.travelapp.api.bookmarks.entity.Bookmarks;
import com.travelapp.api.bookmarks.repository.BookmarksRepository;
import com.travelapp.api.comments.repository.CommentsRepository;
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
public class BookmarksServiceImpl {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    BookmarksRepository bookmarksRepository;

    @Autowired
    @Qualifier("strictModelMapper")
    ModelMapper strictMapper;



    public BookmarksReadDTO createBookmark(BookmarksCreateDTO bookmarksCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (bookmarksCreateDTO.getCreatedBy() != null) {
            String userUid = bookmarksCreateDTO.getCreatedBy().getUserUid();
            if (userUid != null) {
                Optional<Users> optionalUser = usersRepository.findByUserUid(userUid);
                if (optionalUser.isPresent()) {
                    Users createdBy = optionalUser.get();
                    if (bookmarksCreateDTO.getActivity() != null) {
                        Long activityId = bookmarksCreateDTO.getActivity().getActivityId();
                        if (activityId != null) {
                            Optional<Activities> optionalActivity = activitiesRepository.findById(activityId);
                            if (optionalActivity.isPresent()) {
                                Activities activity = optionalActivity.get();
                                Bookmarks bookmarkToCreate = new Bookmarks(activity, createdBy);
                                Bookmarks bookmarkCreated = bookmarksRepository.save(bookmarkToCreate);
                                return strictMapper.map(bookmarkCreated, BookmarksReadDTO.class);
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

    @Transactional
    public void deleteBookmark(String userUid, Long bookmarkId)
            throws EntityNotFoundException, IllegalArgumentException {
        if (userUid == null || bookmarkId == null) {
            throw new IllegalArgumentException("User UID and bookmark ID must be provided.");
        }

        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found.");
        }

        Optional<Bookmarks> bookmarkOpt = bookmarksRepository.findById(bookmarkId);

        if (bookmarkOpt.isEmpty()) {
            throw new EntityNotFoundException("Bookmark with ID " + bookmarkId + " not found.");
        }

        Bookmarks bookmark = bookmarkOpt.get();
        if (!bookmark.getCreatedBy().getUserUid().equals(userUid)) {
            throw new IllegalArgumentException("User is not the owner of this bookmark.");
        }

        bookmarksRepository.delete(bookmark);
    }


}


