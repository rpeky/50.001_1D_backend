package com.travelapp.api.status.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener.DatedEntityListener;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.users.entity.Users;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "status", targetEntity = Users.class)
    private List<Users> users;

    @OneToMany(mappedBy = "status", targetEntity = Activities.class)
    private List<Activities> activities;

    @OneToMany(mappedBy = "status", targetEntity = Itineraries.class)
    private List<Itineraries> itineraries;


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

    public List<Users> getUsers() {
        return users;
    }
    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Activities> getActivities() {
        return activities;
    }
    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }

    public List<Itineraries> getItineraries() {
        return itineraries;
    }
    public void setItineraries(List<Itineraries> itineraries) {
        this.itineraries = itineraries;
    }
}
