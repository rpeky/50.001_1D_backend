package com.travelapp.api.users.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.bookmarks.Bookmarks;
import com.travelapp.api.comments.Comments;
import com.travelapp.api.datedentity.DatedEntity;
import com.travelapp.api.itineraries.Itineraries;
import com.travelapp.api.likes.Likes;
import com.travelapp.api.status.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users extends DatedEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_uid", nullable = false, unique = true)
    private String userUid;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "bio")
    private String bio;

    //owner of rel. with status (fk)
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;


    //Mapping
    //bi-directional mapping (inverse rel.) with activities
    @OneToMany(mappedBy = "createdBy")
    private List<Activities> activities;

    //bi-directional mapping (inverse rel.) with itineraries
    @OneToMany(mappedBy = "createdBy")
    private List<Itineraries> itineraries;

    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "createdBy")
    private List<Comments> Comments;

    //bi-directional mapping (inverse rel.) with bookmarks
    @OneToMany(mappedBy = "createdBy")
    private List<Bookmarks> bookmarks;

    //bi-directional mapping (inverse rel.) with likes
    @OneToMany(mappedBy = "user")
    private List<Likes> likes;



    //Constructor
    //No-Arg Constructor
    public Users() {
    }
    //Full-Arg Constructor
    public Users(Long userId, String userUid, String userName,
                 String email, String bio, Status status,
                 LocalDateTime createdAt) {
        this.userId = userId;
        this.userUid = userUid;
        this.userName = userName;
        this.email = email;
        this.bio = bio;
        this.status = status;
    }


    //Getters and Setters
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getUserUid() {
        return userUid;
    }
    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }


    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
        if (status != null && status.getUser() != this) {
            status.setUser(this);
        }
    }



}
