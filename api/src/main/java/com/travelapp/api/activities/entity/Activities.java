package com.travelapp.api.activities.entity;

import com.travelapp.api.bookmarks.Bookmarks;
import com.travelapp.api.comments.Comments;
import com.travelapp.api.datedentity.DatedEntity;
import com.travelapp.api.likes.Likes;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.trip.Trips;
import com.travelapp.api.users.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activities extends DatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "title", nullable = false)
    private String title;

    //owner of rel. with users (fk)
    @ManyToOne
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "location_link")
    private String locationLink;

    @Column(name = "price")
    private Double price;

    @Column(name = "thumbnail")
    private String thumbnail;

    //owner of rel. with status (fk)
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;


    //Mapping
    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "activity", targetEntity = Comments.class)
    private List<Comments> comments;

    //bi-directional mapping (inverse rel.) with bookmarks
    @OneToOne(mappedBy = "activity", targetEntity = Bookmarks.class)
    private Bookmarks bookmark;

    //bi-directional mapping (inverse rel.) with likes
    @OneToMany(mappedBy = "activity", targetEntity = Likes.class)
    private List<Likes> likes;

    //bi-directional mapping (inverse rel.) with trip
    @OneToMany(mappedBy = "activity", targetEntity = Trips.class)
    private List<Trips> trips;


    //Constructor
    //No-Arg Constructor
    public Activities() {
    }
    //Full-Arg Constructor
    public Activities(Long activityId, String title, Users createdBy,
                      String description, String location, String locationLink,
                      Double price, String thumbnail, Status status,
                      LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.activityId = activityId;
        this.title = title;
        this.createdBy = createdBy;
        this.description = description;
        this.location = location;
        this.locationLink = locationLink;
        this.price = price;
        this.thumbnail = thumbnail;
        this.status = status;
    }


    //Getters and Setters
    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityID) {
        this.activityId = activityID;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public Users getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public String getLocationLink() {
        return locationLink;
    }
    public void setLocationLink(String locationLink) {
        this.locationLink = locationLink;
    }


    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }


    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
        if (status != null && status.getActivity() != this) {
            status.setActivity(this);
        }
    }


}


