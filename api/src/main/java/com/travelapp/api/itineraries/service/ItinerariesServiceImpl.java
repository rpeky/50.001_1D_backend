package com.travelapp.api.itineraries.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.itineraries.DTO.ItinerariesCreateDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesReadDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesUpdateDTO;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itineraries.repository.ItinerariesRepository;
import com.travelapp.api.globalnonsense.mappers.mymappers.MyItinerariesUpdateMapper;
import com.travelapp.api.users.DTO.other.UserOtherCreateDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ItinerariesServiceImpl implements ItinerariesService {
    @Autowired
    private ItinerariesRepository itinerariesRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ActivitiesRepository activitiesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    private ObjectMapper jsonConverter;

    @Override
    public ItinerariesReadDTO getItinerary(Long itineraryId)
            throws EntityNotFoundException {
        Optional<Itineraries> optionalExistingItinerary =  itinerariesRepository.findById(itineraryId);
        if (optionalExistingItinerary.isPresent()) {
            Itineraries itineraryToShow = optionalExistingItinerary.get();
            return defaultMapper.map(itineraryToShow, ItinerariesReadDTO.class);
        } else {
            throw new EntityNotFoundException("Itinerary with ID: "
                    + itineraryId + " not found.");
        }
    }  
    

    @Override
    public List<ItinerariesReadDTO> getAllUserItineraries(String userUid)
    throws EntityNotFoundException {
        if (usersRepository.existsByUserUid(userUid)) {
            List<Itineraries> userItineraries = itinerariesRepository.findByCreatedBy_UserUid(userUid);
            List<ItinerariesReadDTO> userItinerariesToShow = new ArrayList<>();
            for (Itineraries itinerary : userItineraries) {
                ItinerariesReadDTO itineraryToShow = defaultMapper.map(itinerary, ItinerariesReadDTO.class);
                userItinerariesToShow.add(itineraryToShow);
            }
            return userItinerariesToShow;
        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }

    public List<ItinerariesReadDTO> getAllItineraries()
            throws EntityNotFoundException {
        List<Itineraries> allItinerariesList =  itinerariesRepository.findAll();
        List<ItinerariesReadDTO> listToReturn = new ArrayList<>();
        for (Itineraries itinerary : allItinerariesList){
            ItinerariesReadDTO itineraryToAdd = defaultMapper.map(itinerary, ItinerariesReadDTO.class);
            listToReturn.add(itineraryToAdd);
        }
        return listToReturn;
    }

    @Override
    @Transactional
    public ItinerariesReadDTO createItinerary(ItinerariesCreateDTO itinerariesCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {

        Itineraries itineraryToCreate = defaultMapper.map(itinerariesCreateDTO, Itineraries.class);

        if (itineraryToCreate.getStatus() == null) {
            throw new IllegalArgumentException("Status information is required for creation");
        }

        UserOtherCreateDTO userOtherCreateDTO = itinerariesCreateDTO.getCreatedBy();

        if (userOtherCreateDTO != null && userOtherCreateDTO.getUserUid() != null) {

            Optional<Users> optionalItineraryUser = usersRepository.findByUserUid(userOtherCreateDTO.getUserUid());

            if (optionalItineraryUser.isPresent()) {
                Users itineraryUser = optionalItineraryUser.get();
                itineraryToCreate.setCreatedBy(itineraryUser);
                Itineraries itineraryCreated = itinerariesRepository.save(itineraryToCreate);
                entityManager.refresh(itineraryCreated);
                return defaultMapper.map(itineraryCreated, ItinerariesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("User with UID: "
                        + userOtherCreateDTO.getUserUid() + " not found.");
            }
        }
        else {
            throw new IllegalArgumentException("CreatedBy information is required for creation");
        }
    }

    @Override
    @Transactional
    public ItinerariesReadDTO updateItinerary(ItinerariesUpdateDTO itinerariesUpdateDTO)
            throws EntityNotFoundException, IllegalArgumentException {
        if (itinerariesUpdateDTO.getItineraryId().isPresent()
                && itinerariesUpdateDTO.getItineraryId().orElse(null) != null) {

            Long itineraryToUpdateId = itinerariesUpdateDTO.getItineraryId().get();
            Optional<Itineraries> optionalItineraryToUpdate = itinerariesRepository.findById(itineraryToUpdateId);

            if (optionalItineraryToUpdate.isPresent()) {
                Itineraries itineraryToUpdate =  MyItinerariesUpdateMapper
                        .itineraryUpdateMapper(jsonConverter, itinerariesUpdateDTO,
                                optionalItineraryToUpdate.get(), activitiesRepository);
                Itineraries itineraryUpdated = itinerariesRepository.save(itineraryToUpdate);
                entityManager.flush();
                entityManager.clear();
                Itineraries itineraryToShow = itinerariesRepository.findById(itineraryToUpdateId).get();
                return defaultMapper.map(itineraryToShow, ItinerariesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("Itinerary with ID: "
                        + itineraryToUpdateId + " not found.");
            }

        } else {
            throw new IllegalArgumentException("ItineraryID is required for update");
        }

    }

    @Transactional
    @Override
    public void deleteUserItinerary(String userUid, Long itineraryId) throws EntityNotFoundException {
        if (usersRepository.existsByUserUid(userUid)) {
            Optional<Itineraries> optionalItineraryToDelete =  itinerariesRepository.findById(itineraryId);
            if (optionalItineraryToDelete.isPresent()){
                Itineraries itineraryToDelete = optionalItineraryToDelete.get();
                if (itineraryToDelete.getCreatedBy().getUserUid().equals(userUid)){
                    itinerariesRepository.deleteById(itineraryId);
                } else {
                    throw new IllegalArgumentException("Itinerary does not belong to User");
                }
            } else {
                throw new EntityNotFoundException("Itinerary with ID: "
                        + itineraryId + " not found.");
            }
        } else {
            throw new EntityNotFoundException("User with UID: "
                    + userUid + " not found.");
        }
    }


}
