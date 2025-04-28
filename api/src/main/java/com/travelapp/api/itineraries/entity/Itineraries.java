package com.travelapp.api.itineraries.entity;

import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener.DatedEntityListener;
import com.travelapp.api.likes.entity.Likes;
import com.travelapp.api.ratings.entity.Ratings;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;
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
@Table(name = "itineraries")
@EntityListeners(DatedEntityListener.class)
public class Itineraries extends DatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itinerary_id", nullable = false)
    private Long itineraryId;

    @Column(name = "title", nullable = false)
    private String title;

    //owner of rel. with users (fk)
    @ManyToOne
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    @Column(name = "description")
    private String description;

    @Transient
    private Long likes;

    @Transient
    private Long duration;

    @PostLoad
    private void calculateEverything() {
        if (timeline != null) {
            this.duration = (long) timeline.size();
        } else {
            this.duration = 0L;
        }

        if (likesList != null) {
            this.likes = (long) likesList.size();
        } else {
            this.likes = 0L;
        }
    }

    @OneToMany(mappedBy = "itinerary", targetEntity = ItineraryDay.class,
            cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItineraryDay> timeline;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "coverPhoto")
    private String coverPhoto;

    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "itinerary", targetEntity = Comments.class, orphanRemoval = true)
    private List<Comments> comments;

    //owner of rel. with status (fk)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;


    //Mapping
    //bi-directional mapping (inverse rel.) with likes
    @OneToMany(mappedBy = "itinerary", targetEntity = Likes.class, orphanRemoval = true)
    private List<Likes> likesList;

    @OneToMany(mappedBy = "itinerary", targetEntity = Ratings.class, orphanRemoval = true)
    private List<Ratings> ratingsList;


    //Constructor
    //No-Arg Constructor
    public Itineraries() {
    }

    public Itineraries(Long itineraryId, String title, Users createdBy,
                       String description, Long likes, Long duration,
                       List<ItineraryDay> timeline, String thumbnail,
                       String coverPhoto, List<Comments> comments,
                       Status status, List<Likes> likesList, List<Ratings> ratingsList) {
        this.itineraryId = itineraryId;
        this.title = title;
        this.createdBy = createdBy;
        this.description = description;
        this.likes = likes;
        this.duration = duration;
        this.timeline = timeline;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.comments = comments;
        this.status = status;
        this.likesList = likesList;
        this.ratingsList = ratingsList;
    }

    //Getters and Setter
    public Long getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
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

    public List<Ratings> getRatingsList() {
        return ratingsList;
    }
    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
    }

    public Long getDuration() {
        return duration;
    }
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<ItineraryDay> getTimeline() {
        return timeline;
    }
    public void setTimeline(List<ItineraryDay> timeline) {
        this.timeline = timeline;
        this.timeline.forEach(itineraryDay -> itineraryDay.setItinerary(this));
    }

    public List<Comments> getComments() {
        return comments;
    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }
    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }



}