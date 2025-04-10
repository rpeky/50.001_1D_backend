package com.travelapp.api.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.globalnonsense.mappers.mymappers.MyUsersUpdateMapper;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadElseDTO;
import com.travelapp.api.users.DTO.UsersReadSelfDTO;
import com.travelapp.api.users.DTO.UsersUpdateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

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
    private ObjectMapper jsonConverter;


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersReadSelfDTO createUser(UsersCreateDTO usersCreateDTO) {
        Users userToCreate = defaultMapper.map(usersCreateDTO, Users.class);

        if (userToCreate.getStatus() != null) {
            Users userCreated = usersRepository.save(userToCreate);
            return defaultMapper.map(userCreated, UsersReadSelfDTO.class);
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
            return userReadSelfMapper.map(optionalRetrievedUser.get(), UsersReadSelfDTO.class);
        }
        else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

    public UsersReadElseDTO getUserOther(String userUid) throws EntityNotFoundException {
        Optional<Users> optionalRetrievedUser = usersRepository.findByUserUid(userUid);
        if (optionalRetrievedUser.isPresent()){
            UsersReadSelfDTO userRetrieved = userReadSelfMapper.map(optionalRetrievedUser.get(), UsersReadSelfDTO.class);
            return defaultMapper.map(userRetrieved, UsersReadElseDTO.class);
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
