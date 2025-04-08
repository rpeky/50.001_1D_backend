package com.travelapp.api.bookmarks.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.bookmarks.DTO.BookmarksCreateDTO;
import com.travelapp.api.bookmarks.DTO.BookmarksReadDTO;
import com.travelapp.api.bookmarks.entity.Bookmarks;
import com.travelapp.api.bookmarks.repository.BookmarksRepository;
import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier; // Import added
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarksServiceImpl implements BookmarksService {

    @Autowired
    private BookmarksRepository bookmarksRepository;

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private ActivitiesRepository activitiesRepository;

    // Use @Qualifier to specify which bean to inject
    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    private ObjectMapper jsonConverter;

    @Override
    public BookmarksReadDTO createBookmark(BookmarksCreateDTO createDTO) {
        Long userId = createDTO.getUserId();
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Long activityId = createDTO.getActivityId();
        Activities activity = activitiesRepository.findById(activityId)
            .orElseThrow(() -> new EntityNotFoundException("Activity not found: " + activityId));

        Bookmarks bookmarkEntity = new Bookmarks();
        bookmarkEntity.setCreatedBy(user);
        bookmarkEntity.setActivity(activity);

        Bookmarks saved = bookmarksRepository.save(bookmarkEntity);
        return entityToReadDTO(saved);
    }

    @Override
    public BookmarksReadDTO getBookmark(Long bookmarkId) {
        Bookmarks found = bookmarksRepository.findById(bookmarkId)
            .orElseThrow(() -> new BookmarkNotFoundException(bookmarkId));
        return entityToReadDTO(found);
    }

    @Override
    public List<BookmarksReadDTO> getAllBookmarks() {
        List<Bookmarks> found = bookmarksRepository.findAll();
        return found.stream()
                    .map(this::entityToReadDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<BookmarksReadDTO> getBookmarksByUser(Long userId) {
        List<Bookmarks> found = bookmarksRepository.findDistinctByCreatedByUserId(userId);
        return found.stream()
                    .map(this::entityToReadDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public List<BookmarksReadDTO> getBookmarksByUserUuid(String userUid) {
        // Look up the user by UID.
        Users user = usersRepository.findByUserUid(userUid)
            .orElseThrow(() -> new EntityNotFoundException("User with UID " + userUid + " not found"));
        
        // Now retrieve bookmarks using the numeric userId.
        return getBookmarksByUser(user.getUserId());
    }

    @Override
    public long countBookmarksByActivity(Long activityId) {
        return bookmarksRepository.countByActivity_ActivityId(activityId);
    }

    @Override
    public void deleteBookmark(Long bookmarkId) {
        Bookmarks existing = bookmarksRepository.findById(bookmarkId)
            .orElseThrow(() -> new BookmarkNotFoundException(bookmarkId));
        bookmarksRepository.delete(existing);
    }

    private BookmarksReadDTO entityToReadDTO(Bookmarks entity) {
        BookmarksReadDTO dto = new BookmarksReadDTO();
        dto.setBookmarkId(entity.getBookmarkId());
        dto.setUserId(entity.getCreatedBy().getUserId());
        dto.setActivityId(entity.getActivity().getActivityId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }
}

