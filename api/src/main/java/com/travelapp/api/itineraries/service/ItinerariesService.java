package com.travelapp.api.itineraries.service;

import com.travelapp.api.itineraries.DTO.ItinerariesCreateDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesReadDTO;
import com.travelapp.api.itineraries.DTO.ItinerariesUpdateDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface ItinerariesService {
    ItinerariesReadDTO getItinerary(Long itineraryId);
    List<ItinerariesReadDTO> getAllUserItineraries (String userUid);
    ItinerariesReadDTO createItinerary(ItinerariesCreateDTO itinerariesCreateDTO);
    ItinerariesReadDTO updateItinerary(ItinerariesUpdateDTO itinerariesUpdateDTO);
    void deleteUserItinerary(String userUid, Long itineraryId);
}
