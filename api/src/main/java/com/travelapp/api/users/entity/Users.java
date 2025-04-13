package com.travelapp.api.users.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.bookmarks.entity.Bookmarks;
import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.likes.entity.Likes;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.users.userfollows.entity.UserFollows;

import java.util.ArrayList;
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
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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

    @OneToMany(mappedBy = "createdBy", targetEntity = Activities.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Activities> activities;

    @OneToMany(mappedBy = "createdBy", targetEntity = Itineraries.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Itineraries> allItineraries;

    @Transient
    private List<Itineraries> myTrips = new ArrayList<>();

    @Transient
    private List<Itineraries> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", targetEntity = Bookmarks.class, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Bookmarks> bookmarks;

    @Transient
    private Long activitiesCount;

    @PostLoad
    void postLoadFunction(){
        if (activities != null) {
            this.activitiesCount = (long) activities.size();
        } else {
            this.activitiesCount = 0L;
        }

        for (Itineraries itinerary : allItineraries) {
            if (itinerary.getStatus() != null) {
                if (itinerary.getStatus().getStatusName().equals("Published")) {
                    itineraries.add(itinerary);
                } else {
                    myTrips.add(itinerary);
                }
            }
        }
    }

    //owner of rel. with status (fk)
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "status", referencedColumnName = "status_id", nullable = false)
    private Status status;


    //bi-directional mapping (inverse rel.) with follower to get following list
    @OneToMany(mappedBy = "follower", targetEntity = UserFollows.class, orphanRemoval = true)
    private List<UserFollows> followingList;

    //bi-directional mapping (inverse rel.) with following to get follower list
    @OneToMany(mappedBy = "following", targetEntity = UserFollows.class, orphanRemoval = true)
    private List<UserFollows> followerList;

    //bi-directional mapping (inverse rel.) with comments
    @OneToMany(mappedBy = "createdBy", targetEntity = Comments.class, orphanRemoval = true)
    private List<Comments> Comments;

    //bi-directional mapping (inverse rel.) with likes
    @OneToMany(mappedBy = "createdBy", targetEntity = Likes.class, orphanRemoval = true)
    private List<Likes> likes;

    //Constructor
    //No-Arg Constructor
    public Users() {
    }
    //Full-Arg Constructor


    public Users(Long userId, String userUid, String userName,
                 String email, String bio, List<Activities> activities,
                 List<Itineraries> allItineraries, List<Itineraries> myTrips,
                 List<Itineraries> itineraries, Status status) {
        this.userId = userId;
        this.userUid = userUid;
        this.userName = userName;
        this.email = email;
        this.bio = bio;
        this.activities = activities;
        this.allItineraries = allItineraries;
        this.myTrips = myTrips;
        this.itineraries = itineraries;
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

    public List<Activities> getActivities() {
        return activities;
    }
    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }

    public List<Itineraries> getAllItineraries() {
        return allItineraries;
    }
    public void setAllItineraries(List<Itineraries> allItineraries) {
        this.allItineraries = allItineraries;
    }

    public List<Itineraries> getMyTrips() {
        return myTrips;
    }
    public void setMyTrips(List<Itineraries> myTrips) {
        this.myTrips = myTrips;
    }

    public List<Itineraries> getItineraries() {
        return itineraries;
    }
    public void setItineraries(List<Itineraries> itineraries) {
        this.itineraries = itineraries;
    }

    public List<Bookmarks> getBookmarks() {
        return bookmarks;
    }
    public void setBookmarks(List<Bookmarks> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Long getActivitiesCount() {
        return activitiesCount;
    }
    public void setActivitiesCount(Long activitiesCount) {
        this.activitiesCount = activitiesCount;
    }

    public List<UserFollows> getFollowingList() {
        return followingList;
    }
    public void setFollowingList(List<UserFollows> followingList) {
        this.followingList = followingList;
    }

    public List<UserFollows> getFollowerList() {
        return followerList;
    }
    public void setFollowerList(List<UserFollows> followerList) {
        this.followerList = followerList;
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
