package com.travelapp.api.itineraries.entity;

import com.travelapp.api.comments.Comments;
import com.travelapp.api.globalnonsense.datedentity.DatedEntity;
import com.travelapp.api.globalnonsense.datedentity.datedentitylistener.DatedEntityListener;
import com.travelapp.api.likes.Likes;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;
import com.travelapp.api.users.entity.Users;

import org.hibernate.annotations.Formula;

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

    @Column(name = "price_range")
    private Double priceRange;

    @Transient
    private Long duration;

    @PostLoad
    private void calculateDuration() {
        if (timeline != null) {
            this.duration = (long) timeline.size();
        } else {
            this.duration = 0L;
        }
    }

    @OneToMany(mappedBy = "itinerary", targetEntity = ItineraryDay.class,
            cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ItineraryDay> timeline;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "coverPhoto")
    private String coverPhoto;

    //owner of rel. with status (fk)
    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;


    //Mapping
    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "itinerary")
    private List<Comments> comments;

    //bi-directional mapping (inverse rel.) with likes
    @OneToMany(mappedBy = "itinerary", targetEntity = Likes.class)
    private List<Likes> likes;


    //Constructor
    //No-Arg Constructor
    public Itineraries() {
    }
    //Full-Arg Constructor
    public Itineraries(Long itineraryId, String title, Users createdBy,
                       String description, Double priceRange, Long duration,
                       List<ItineraryDay> timeline, String thumbnail,
                       String coverPhoto, Status status) {
        this.itineraryId = itineraryId;
        this.title = title;
        this.createdBy = createdBy;
        this.description = description;
        this.priceRange = priceRange;
        this.duration = duration;
        this.timeline = timeline;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
        this.status = status;
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

    public Double getPriceRange() {
        return priceRange;
    }
    public void setPriceRange(Double priceRange) {
        this.priceRange = priceRange;
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
        if (status != null && status.getItinerary() != this) {
            status.setItinerary(this);
        }
    }



}