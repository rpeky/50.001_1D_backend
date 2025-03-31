package com.travelapp.api.status;

import com.travelapp.api.activities.Activities;
import com.travelapp.api.itineraries.Itineraries;
import com.travelapp.api.users.Users;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;

    @OneToOne(mappedBy = "status")
    private Users user;

    @OneToOne(mappedBy = "status")
    private Activities activity;

    @OneToOne(mappedBy = "status")
    private Itineraries itineraries;

    //Constructors
    //No-Arg Constructor
    public Status() {
    }
    //Full-Arg Constructor
    public Status(Long statusId, String statusName,
                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.statusId = statusId;
        this.statusName = statusName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    //Getters and Setters
    public Long getStatusId() {
        return statusId;
    }
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }


    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
