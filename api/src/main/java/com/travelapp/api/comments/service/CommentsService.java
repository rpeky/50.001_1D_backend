package com.travelapp.api.comments.service;

import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;

public interface CommentsService {

    CommentsReadDTO createCommentActivity(CommentsCreateDTO commentsCreateDTO);
    CommentsReadDTO createCommentItinerary(CommentsCreateDTO commentsCreateDTO);

}
