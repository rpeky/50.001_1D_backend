package com.travelapp.api.activities.entity;

import com.travelapp.api.activities.activitymedia.entity.Media;
import com.travelapp.api.bookmarks.entity.Bookmarks;
import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener.DatedEntityListener;
import com.travelapp.api.itinerarydayactivity.daysactivity.entity.DayActivity;
import com.travelapp.api.likes.entity.Likes;
import com.travelapp.api.ratings.entity.Ratings;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.users.entity.Users;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "activities")
@EntityListeners(DatedEntityListener.class)
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

    @OneToMany(mappedBy = "activity", targetEntity = Media.class,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> medias;

    @Transient
    private Long likes;

    @PostLoad
    void calculateLikes() {
        if (likesList != null) {
            this.likes = (long) likesList.size();
        } else {
            this.likes = 0L;
        }
    }

    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "activity", targetEntity = Comments.class, orphanRemoval = true)
    private List<Comments> comments;

    //owner of rel. with status (fk)
    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;


    //Mapping

    //bi-directional mapping (inverse rel.) with bookmarks
    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Bookmarks> bookmarks;

    //bi-directional mapping (inverse rel.) with DaysActivity
    @OneToMany(mappedBy = "activity", targetEntity = DayActivity.class)
    private List<DayActivity> days;

    @OneToMany(mappedBy = "activity", targetEntity = Likes.class, orphanRemoval = true)
    private List<Likes> likesList;

    @OneToMany(mappedBy = "activity", targetEntity = Ratings.class, orphanRemoval = true)
    private List<Ratings> ratingsList;

    //Constructor
    //No-Arg Constructor
    public Activities() {
    }
    //Full-Arg Constructor
    public Activities(Long activityId, String title, Users createdBy,
                      String description, String location, String locationLink,
                      Double price, String thumbnail, List<Media> medias, Status status) {
        this.activityId = activityId;
        this.title = title;
        this.createdBy = createdBy;
        this.description = description;
        this.location = location;
        this.locationLink = locationLink;
        this.price = price;
        this.thumbnail = thumbnail;
        this.medias = medias;
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

    public List<Media> getMedias() {
        return medias;
    }
    public void setMedias(List<Media> media) {
        this.medias = media;
        this.medias.forEach(m -> m.setActivity(this));
    }

    public Long getLikes() {
        return likes;
    }
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public List<Likes> getLikesList() {
        return likesList;
    }
    public void setLikesList(List<Likes> likesList) {
        this.likesList = likesList;
    }

    public List<Comments> getComments() {
        return comments;
    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }
    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
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


