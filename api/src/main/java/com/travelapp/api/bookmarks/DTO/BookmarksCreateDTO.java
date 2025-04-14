package com.travelapp.api.bookmarks.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;

public class BookmarksCreateDTO {

    private UsersOtherCreateDTO createdBy;
    private ActivitiesOtherCUDTO activity;

    public UsersOtherCreateDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherCreateDTO createdBy) {
        this.createdBy = createdBy;
    }

    public ActivitiesOtherCUDTO getActivity() {
        return activity;
    }
    public void setActivity(ActivitiesOtherCUDTO activity) {
        this.activity = activity;
    }


}
