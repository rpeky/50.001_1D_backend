package com.travelapp.api.itineraries;

import com.travelapp.api.comments.Comments;
import com.travelapp.api.likes.Likes;
import com.travelapp.api.status.Status;
import com.travelapp.api.trip.Trips;
import com.travelapp.api.users.Users;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "itineraries")
public class Itineraries {

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

    @Column(name = "duration")
    private Long duration;

    @Column(name = "thumbnail")
    private String thumbnail;

    //owner of rel. with status (fk)
    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;


    //Mapping
    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "itinerary")
    private List<Comments> Comments;

    //bi-directional mapping (inverse rel.) with likes
    @OneToOne(mappedBy = "itinerary", targetEntity = Likes.class)
    private Likes like;

    //bi-directional mapping (inverse rel.) with trip
    @OneToMany(mappedBy = "itinerary", targetEntity = Trips.class)
    private List<Trips> trips;


    //Constructor
    //No-Arg Constructor
    public Itineraries() {
    }
    //Full-Arg Constructor
    public Itineraries(Long itineraryId, String title, Users createdBy,
                       String description, Double priceRange, Long duration,
                       String thumbnail, Status status, LocalDateTime createdAt,
                       LocalDateTime modifiedAt) {
        this.itineraryId = itineraryId;
        this.title = title;
        this.createdBy = createdBy;
        this.description = description;
        this.priceRange = priceRange;
        this.duration = duration;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
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
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }


}