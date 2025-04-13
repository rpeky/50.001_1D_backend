package com.travelapp.api.users.usersettings.service;

import com.travelapp.api.globalnonsense.mappers.mymappers.MyUserSettingsUpdateMapper;
import com.travelapp.api.users.DTO.other.UsersOtherUpdateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.users.usersettings.DTO.UserSettingsReadDTO;
import com.travelapp.api.users.usersettings.DTO.UserSettingsUpdateDTO;
import com.travelapp.api.users.usersettings.entity.UserSettings;
import com.travelapp.api.users.usersettings.repository.UserSettingsRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserSettingsServiceImpl {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserSettingsRepository userSettingsRepository;

    @Autowired
    @Qualifier("strictModelMapper")
    ModelMapper strictMapper;


    public void createUserSettings(Users user) {
        UserSettings settings = new UserSettings(user);
        userSettingsRepository.save(settings);
    }

    public UserSettingsReadDTO getUserSettings(String userUid) throws EntityNotFoundException {

        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found.");
        }

        UserSettings settings = userSettingsRepository.findByUser_UserUid(userUid)
                .orElseThrow(() -> new EntityNotFoundException("UserSettings not found for user UID " + userUid));

        return strictMapper.map(settings, UserSettingsReadDTO.class);
    }


    public UserSettingsReadDTO updateUserSettings(UserSettingsUpdateDTO updateDTO)
            throws EntityNotFoundException, IllegalArgumentException {

        if (!updateDTO.getUser().isPresent()) {
            throw new IllegalArgumentException("User information is required for settings update.");
        }

        UsersOtherUpdateDTO user = updateDTO.getUser().get();

        if (!user.getUserUid().isPresent()) {
            throw new IllegalArgumentException("User UID is required for settings update.");
        }

        String userUid = user.getUserUid().get();

        if (!usersRepository.existsByUserUid(userUid)) {
            throw new EntityNotFoundException("User with UID " + userUid + " not found.");
        }

        Optional<UserSettings> optionalUserSettings = userSettingsRepository.findByUser_UserUid(userUid);

        if (optionalUserSettings.isEmpty()) {
            throw new EntityNotFoundException("UserSettings not found for user UID " + userUid);
        }

        UserSettings userSettings = optionalUserSettings.get();
        MyUserSettingsUpdateMapper.applyUpdates(updateDTO, userSettings);
        UserSettings settingsSaved = userSettingsRepository.save(userSettings);

        return strictMapper.map(settingsSaved, UserSettingsReadDTO.class);
    }


}
