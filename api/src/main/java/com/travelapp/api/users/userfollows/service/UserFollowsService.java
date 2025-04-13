package com.travelapp.api.users.userfollows.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.users.userfollows.DTO.UserFollowsCreateDTO;
import com.travelapp.api.users.userfollows.DTO.UserFollowsReadDTO;
import com.travelapp.api.users.userfollows.entity.UserFollows;
import com.travelapp.api.users.userfollows.repository.UserFollowsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserFollowsService {

    @Autowired
    @Qualifier("defaultModelMapper")
    ModelMapper defaultMapper;

    @Autowired
    @Qualifier("strictModelMapper")
    ModelMapper strictMapper;

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserFollowsRepository userFollowsRepository;


    public UserFollowsReadDTO createFollow(UserFollowsCreateDTO userFollowsCreateDTO){
        if (userFollowsCreateDTO.getFollower() != null) {
            String followerId = userFollowsCreateDTO.getFollower().getUserUid();
            if (followerId != null) {
                Optional<Users> optionalFollower = usersRepository.findByUserUid(followerId);
                if (optionalFollower.isPresent()) {
                    Users follower = optionalFollower.get();

                    if (userFollowsCreateDTO.getFollowing() != null) {
                        String followingId = userFollowsCreateDTO.getFollowing().getUserUid();
                        if (followingId != null) {
                            if (!followerId.equals(followingId)){
                                Optional<Users> optionalFollowing = usersRepository.findByUserUid(followingId);
                                if (optionalFollowing.isPresent()) {

                                    Users following = optionalFollowing.get();

                                    UserFollows userFollow = new UserFollows(follower, following);

                                    UserFollows userFollowCreated = userFollowsRepository.save(userFollow);

                                    return strictMapper.map(userFollowCreated, UserFollowsReadDTO.class);

                                } else {
                                    throw new EntityNotFoundException("User with UID: "
                                            + followingId + " not found.");
                                }
                            } else {
                                throw new IllegalArgumentException("User cannot follow themselves.");
                            }

                        } else {
                            throw new IllegalArgumentException("Following User UID information is required for creation.");
                        }
                    } else {
                        throw new IllegalArgumentException("Following information is required for creation");
                    }


                } else {
                    throw new EntityNotFoundException("User with UID: "
                            + followerId + " not found.");
                }
            } else {
                throw new IllegalArgumentException("Follower User UID information is required for creation");
            }
        } else {
            throw new IllegalArgumentException("Follower information is required for creation");
        }

    }

    @Transactional
    public void deleteUserFollow(String userUid, Long followId) {
        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found");
        }

        Optional<UserFollows> optionalUserFollows = userFollowsRepository.findById(followId);
        if (optionalUserFollows.isPresent()) {
            userFollowsRepository.delete(optionalUserFollows.get());
        } else {
            throw new EntityNotFoundException("Follow with ID " + followId + " not found.");
        }
    }



}
