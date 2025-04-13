package com.travelapp.api.status.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener.DatedEntityListener;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.users.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "status")
@EntityListeners(DatedEntityListener.class)
public class Status extends DatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "status_name", nullable = false)
    private String statusName;


    //Mapping
    @OneToOne(mappedBy = "status", targetEntity = Users.class)
    private Users user;

    @OneToOne(mappedBy = "status", targetEntity = Activities.class)
    private Activities activity;

    @OneToOne(mappedBy = "status", targetEntity = Itineraries.class)
    private Itineraries itinerary;


    //Constructors
    //No-Arg Constructors
    public Status() {
    }

    //Full-Arg Constructors
    public Status(Long statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
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

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }

    public Itineraries getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
    }
}
