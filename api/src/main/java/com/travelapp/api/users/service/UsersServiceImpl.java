package com.travelapp.api.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelapp.api.mappers.mymappers.MyUsersUpdateMapper;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.users.DTO.UsersCreateDTO;
import com.travelapp.api.users.DTO.UsersReadDTO;
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
    private ObjectMapper jsonConverter;


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersReadDTO createUser(UsersCreateDTO usersCreateDTO) {
        Users userToCreate = defaultMapper.map(usersCreateDTO, Users.class);

        if (userToCreate.getStatus() == null) {
            Status activeStatus = new Status();
            activeStatus.setStatusName("ACTIVE");
            activeStatus.setUser(userToCreate); // Maintain the bi-directional link
            userToCreate.setStatus(activeStatus);
        }

            Users userCreated = usersRepository.save(userToCreate);
        return defaultMapper.map(userCreated, UsersReadDTO.class);
    }

    @Override
    public UsersReadDTO updateUser(UsersUpdateDTO usersUpdateDTO) throws EntityNotFoundException, IllegalArgumentException {

        if (usersUpdateDTO.getUserUid().isPresent()) {
            String userUid = usersUpdateDTO.getUserUid().get();
            Optional<Users> optionalExistingUser =
                    usersRepository.findByUserUid(userUid);

            if (optionalExistingUser.isPresent()) {
                Users userUpdated = MyUsersUpdateMapper
                        .usersUpdateMapper(jsonConverter, usersUpdateDTO, optionalExistingUser.get());
                return defaultMapper.map(usersRepository.save(userUpdated), UsersReadDTO.class);

            } else {
                throw new EntityNotFoundException("User with UID: "
                        + userUid + " not found.");
            }
        }
        else {
            throw new IllegalArgumentException("User UID is required for update.");
        }

    }

    @Override
    public UsersReadDTO getUser(String userUid) throws EntityNotFoundException {
        Optional<Users> optionalRetrievedUser = usersRepository.findByUserUid(userUid);
        if (optionalRetrievedUser.isPresent()){
            return defaultMapper.map(optionalRetrievedUser.get(), UsersReadDTO.class);
        }
        else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

    @Override
    public List<UsersReadDTO> getAllUsers(){
        List<Users> allUsers = usersRepository.findAll();
        List<UsersReadDTO> listUsersToShow = new ArrayList<>();
        for (Users user: allUsers) {
            UsersReadDTO userToShow = defaultMapper.map(user, UsersReadDTO.class);
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
