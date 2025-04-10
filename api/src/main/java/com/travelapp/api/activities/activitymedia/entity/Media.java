package com.travelapp.api.activities.activitymedia.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentity.DatedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activity_media")
public class Media extends DatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long mediaId;

    @ManyToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id", nullable = false)
    private Activities activity;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    public Media() {
    }

    public Media(Long mediaId, Activities activity, String type, String url) {
        this.mediaId = mediaId;
        this.activity = activity;
        this.type = type;
        this.url = url;
    }

    public Long getMediaId() {
        return mediaId;
    }
    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }


}
