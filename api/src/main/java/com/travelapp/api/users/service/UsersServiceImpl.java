package com.travelapp.api.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.AAsecurity.DTO.UsersLoginConfirmationDTO;
import com.travelapp.api.shop.service.ShopService;
import com.travelapp.api.globalnonsense.mappers.mymappers.MyUsersUpdateMapper;
import com.travelapp.api.status.DTO.external.StatusCreateDTO;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadElseDTO;
import com.travelapp.api.users.DTO.UsersReadSelfDTO;
import com.travelapp.api.users.DTO.UsersUpdateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.users.extrainfo.UserExtraInfo;
import com.travelapp.api.users.extrainfo.UsersExtraInfoCalculation;
import com.travelapp.api.users.userfollows.repository.UserFollowsRepository;
import com.travelapp.api.users.usersettings.service.UserSettingsServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    @Qualifier("userReadSelfMapper")
    private ModelMapper userReadSelfMapper;

    @Autowired
    @Qualifier("strictModelMapper")
    private ModelMapper strictMapper;

    @Autowired
    private ObjectMapper jsonConverter;


    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserFollowsRepository userFollowsRepository;
    @Autowired
    private UserSettingsServiceImpl userSettingsService;
    @Autowired
    private ShopService shopService;



    @Override
    public void createUser(UsersCreateDTO usersCreateDTO) {

        StatusCreateDTO statusCreateDTO = usersCreateDTO.getStatus();

        if (statusCreateDTO.getStatusId() == null) {
            statusCreateDTO.setStatusId(0L);
        }

        Users userToCreate = defaultMapper.map(usersCreateDTO, Users.class);

        if (userToCreate.getStatus() != null) {
            Users userCreated = usersRepository.save(userToCreate);
            userSettingsService.createUserSettings(userCreated);
            shopService.createCart(userCreated);
        } else {
            throw new IllegalArgumentException("Status Information is required for user creation");
        }
    }


    @Override
    public UsersReadSelfDTO updateUser(UsersUpdateDTO usersUpdateDTO) throws EntityNotFoundException, IllegalArgumentException {

        if (usersUpdateDTO.getUserUid().isPresent()) {
            String userUid = usersUpdateDTO.getUserUid().get();
            Optional<Users> optionalExistingUser =
                    usersRepository.findByUserUid(userUid);

            if (optionalExistingUser.isPresent()) {
                Users userUpdated = MyUsersUpdateMapper
                        .usersUpdateMapper(jsonConverter, usersUpdateDTO, optionalExistingUser.get());
                return defaultMapper.map(usersRepository.save(userUpdated), UsersReadSelfDTO.class);

            } else {
                throw new EntityNotFoundException("User with UID: "
                        + userUid + " not found.");
            }
        }
        else {
            throw new IllegalArgumentException("User UID information is required for update.");
        }

    }

    @Override
    public UsersReadSelfDTO getUserSelf(String userUid) throws EntityNotFoundException {
        Optional<Users> optionalRetrievedUser = usersRepository.findByUserUid(userUid);
        if (optionalRetrievedUser.isPresent()){
            Users retrievedUser = optionalRetrievedUser.get();

            UserExtraInfo extraInfo =
                    UsersExtraInfoCalculation.calculateUserExtraInfo(retrievedUser, userFollowsRepository, strictMapper);

            UsersReadSelfDTO userRetrieved = userReadSelfMapper.map(optionalRetrievedUser.get(), UsersReadSelfDTO.class);

            userRetrieved.setFollowerCount(extraInfo.getFollowerCount());
            userRetrieved.setFollowingCount(extraInfo.getFollowingCount());
            userRetrieved.setLikesCount(extraInfo.getLikesCount());
            userRetrieved.setFollowerList(extraInfo.getFollowerList());
            userRetrieved.setFollowingList(extraInfo.getFollowingList());

            return userRetrieved;
        }
        else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

    public UsersReadElseDTO getUserOther(String userUid) throws EntityNotFoundException {
        Optional<Users> optionalRetrievedUser = usersRepository.findByUserUid(userUid);
        if (optionalRetrievedUser.isPresent()){
            Users retrievedUser = optionalRetrievedUser.get();

            UserExtraInfo extraInfo =
                    UsersExtraInfoCalculation.calculateUserExtraInfo(retrievedUser, userFollowsRepository, strictMapper);

            UsersReadElseDTO userRetrieved = userReadSelfMapper.map(optionalRetrievedUser.get(), UsersReadElseDTO.class);

            userRetrieved.setFollowerCount(extraInfo.getFollowerCount());
            userRetrieved.setFollowingCount(extraInfo.getFollowingCount());
            userRetrieved.setLikesCount(extraInfo.getLikesCount());

            return userRetrieved;
        }
        else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

    @Override
    public List<UsersReadSelfDTO> getAllUsers(){
        List<Users> allUsers = usersRepository.findAll();
        List<UsersReadSelfDTO> listUsersToShow = new ArrayList<>();
        for (Users user: allUsers) {
            UsersReadSelfDTO userToShow = defaultMapper.map(user, UsersReadSelfDTO.class);
            listUsersToShow.add(userToShow);
        }
        return listUsersToShow;
    }

    @Transactional
    @Override
    public void deleteUser(String userUid) throws EntityNotFoundException {
        Optional<Users> optionalRetrievedUser = usersRepository.findByUserUid(userUid);
        if (optionalRetrievedUser.isPresent()){
            usersRepository.deleteByUserUid(userUid);
        }
        else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

}
