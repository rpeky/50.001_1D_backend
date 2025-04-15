package com.travelapp.api.itineraries.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.itineraries.DTO.ItinerariesCreateDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesReadDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesUpdateDTO;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itineraries.repository.ItinerariesRepository;
import com.travelapp.api.globalnonsense.mappers.mymappers.MyItinerariesUpdateMapper;
import com.travelapp.api.ratings.RatingsCalculator.RatingsCalculator;
import com.travelapp.api.ratings.entity.Ratings;
import com.travelapp.api.ratings.repository.RatingsRepository;
import com.travelapp.api.status.DTO.external.StatusCreateDTO;
import com.travelapp.api.status.repository.StatusRepository;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;
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
    @Autowired
    private RatingsRepository ratingsRepository;
    @Autowired
    private StatusRepository statusRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    @Qualifier("strictModelMapper")
    private ModelMapper strictMapper;

    @Autowired
    private ObjectMapper jsonConverter;

    @Override
    public ItinerariesReadDTO getItinerary(Long itineraryId)
            throws EntityNotFoundException {
        Optional<Itineraries> optionalExistingItinerary =  itinerariesRepository.findById(itineraryId);
        if (optionalExistingItinerary.isPresent()) {
            Itineraries itineraryRetrieved = optionalExistingItinerary.get();
            ItinerariesReadDTO itineraryToShow = strictMapper.map(itineraryRetrieved, ItinerariesReadDTO.class);

            List<Ratings> allItineraryRatings = ratingsRepository.findAllWhereActivityIsNull();

            itineraryToShow.setRatings(RatingsCalculator
                    .computeBayesianAverageActItin(itineraryRetrieved.getRatingsList(), allItineraryRatings, 10));
            itineraryToShow.setPriceRange(GetItineraryPriceRange.calculatePriceRange(itineraryRetrieved));

            return itineraryToShow;
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
                ItinerariesReadDTO itineraryToShow = strictMapper.map(itinerary, ItinerariesReadDTO.class);

                itineraryToShow.setRatings(RatingsCalculator.computeAverageRating(itinerary.getRatingsList()));
                itineraryToShow.setPriceRange(GetItineraryPriceRange.calculatePriceRange(itinerary));

                userItinerariesToShow.add(itineraryToShow);
            }

            userItinerariesToShow.sort((i1, i2) -> i1.getModifiedAt().compareTo(i2.getModifiedAt()));

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
            ItinerariesReadDTO itineraryToAdd = strictMapper.map(itinerary, ItinerariesReadDTO.class);

            itineraryToAdd.setRatings(RatingsCalculator.computeAverageRating(itinerary.getRatingsList()));
            itineraryToAdd.setPriceRange(GetItineraryPriceRange.calculatePriceRange(itinerary));

            listToReturn.add(itineraryToAdd);
        }

        listToReturn.sort((i1, i2) -> i1.getModifiedAt().compareTo(i2.getModifiedAt()));

        return listToReturn;
    }

    @Override
    @Transactional
    public ItinerariesReadDTO createItinerary(ItinerariesCreateDTO itinerariesCreateDTO)
            throws EntityNotFoundException, IllegalArgumentException {

        if (itinerariesCreateDTO.getStatus() == null) {
            throw new IllegalArgumentException("Status information is required for creation");
        }
        StatusCreateDTO statusCreateDTO = itinerariesCreateDTO.getStatus();
        if (statusCreateDTO.getStatusId() == null) {
            statusCreateDTO.setStatusId(0L);
            itinerariesCreateDTO.setStatus(statusCreateDTO);
        }

        Itineraries itineraryToCreate = defaultMapper.map(itinerariesCreateDTO, Itineraries.class);

        UsersOtherCreateDTO usersOtherCreateDTO = itinerariesCreateDTO.getCreatedBy();

        if (usersOtherCreateDTO != null && usersOtherCreateDTO.getUserUid() != null) {

            Optional<Users> optionalItineraryUser = usersRepository.findByUserUid(usersOtherCreateDTO.getUserUid());

            if (optionalItineraryUser.isPresent()) {
                Users itineraryUser = optionalItineraryUser.get();
                itineraryToCreate.setCreatedBy(itineraryUser);
                Itineraries itineraryCreated = itinerariesRepository.save(itineraryToCreate);
                entityManager.refresh(itineraryCreated);
                return strictMapper.map(itineraryCreated, ItinerariesReadDTO.class);
            }
            else {
                throw new EntityNotFoundException("User with UID: "
                        + usersOtherCreateDTO.getUserUid() + " not found.");
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
                                optionalItineraryToUpdate.get(), activitiesRepository, statusRepository);
                Itineraries itineraryUpdated = itinerariesRepository.save(itineraryToUpdate);
                entityManager.flush();
                entityManager.clear();
                Itineraries itineraryToShow = itinerariesRepository.findById(itineraryToUpdateId).get();
                return strictMapper.map(itineraryToShow, ItinerariesReadDTO.class);
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
