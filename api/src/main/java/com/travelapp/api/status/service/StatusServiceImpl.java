package com.travelapp.api.status.service;

import com.travelapp.api.status.DTO.internal.StatusCreateDTOInternal;
import com.travelapp.api.status.DTO.internal.StatusReadDTOInternal;
import com.travelapp.api.status.DTO.internal.StatusUpdateDTOInternal;
import com.travelapp.api.status.entity.Status;
import com.travelapp.api.status.repository.StatusRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    @Qualifier("defaultModelMapper")
    private ModelMapper defaultMapper;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public StatusReadDTOInternal getStatus(Long statusId) throws EntityNotFoundException{
        Optional<Status> optionalRetrievedStatus = statusRepository.findById(statusId);
        if (optionalRetrievedStatus.isPresent()) {
            return defaultMapper.map(optionalRetrievedStatus.get(), StatusReadDTOInternal.class);
        } else {
            throw new EntityNotFoundException("Status with id: "
                    + statusId + " not found.");
        }
    }

    @Override
    public StatusReadDTOInternal createStatus(StatusCreateDTOInternal statusCreateDTOInternal) {
        Status statusToCreate = defaultMapper.map(statusCreateDTOInternal, Status.class);
        Status statusCreated = statusRepository.save(statusToCreate);
        //showcase the object created back to controller through ReadDTO
        /*Edit_0304:
        when status is created, java side makes it with a timestamp for modification
        However, insertable = false in Status (entity) prevents that from being inserted
        and so at creation modified at is saved as null, but class is same as creation
        because of @UpdateTimestamp annotation, which recognises creation as an update.
        So, we will retrieve the object created one more time and map that instead.*/
        Status statusToShow = statusRepository.findById(statusCreated.getStatusId()).get();
        return defaultMapper.map(statusToShow, StatusReadDTOInternal.class);
    }

    @Override
    public StatusReadDTOInternal updateStatus(StatusUpdateDTOInternal statusUpdateDTOInternal) throws EntityNotFoundException{
        Optional<Status> optionalExistingStatus = statusRepository.findById(statusUpdateDTOInternal.getStatusId());
        if (optionalExistingStatus.isPresent()) {
            Status statusToUpdate = optionalExistingStatus.get();
            defaultMapper.map(statusUpdateDTOInternal, statusToUpdate);
            //showcase the object updates back to controller through ReadDTO
            return defaultMapper.map(statusRepository.save(statusToUpdate), StatusReadDTOInternal.class);
        }
        else {
            throw new EntityNotFoundException("Status with id: "
                    + statusUpdateDTOInternal.getStatusId() + " not found.");
        }
    }

    @Override
    public void deleteStatus(Long statusId) throws EntityNotFoundException{
        Optional<Status> optionalExistingStatus = statusRepository.findById(statusId);
        if (optionalExistingStatus.isPresent()) {
            statusRepository.deleteById(statusId);
        }
        else {
            throw new EntityNotFoundException("Status with id: "
                    + statusId + " not found.");
        }
    }

    @Override
    public List<StatusReadDTOInternal> getAllStatus() {
        List<Status> listOfStatus = statusRepository.findAll();
        List<StatusReadDTOInternal> listOfStatusToShow = new ArrayList<StatusReadDTOInternal>();
        for (Status statusRetrieved: listOfStatus) {
            StatusReadDTOInternal statusToShow = defaultMapper.map(statusRetrieved, StatusReadDTOInternal.class);
            listOfStatusToShow.add(statusToShow);
        }
        return listOfStatusToShow;
    }

}
