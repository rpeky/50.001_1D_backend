package com.travelapp.api.comments.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherReadDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

public class CommentsReadDTO {

    private Long commentId;
    private UsersOtherReadDTO createdBy;
    private String comment;

    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public UsersOtherReadDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherReadDTO createdBy) {
        this.createdBy = createdBy;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
