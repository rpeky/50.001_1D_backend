package com.travelapp.api.status.controller;

import com.travelapp.api.status.DTO.internal.StatusCreateDTOInternal;
import com.travelapp.api.status.DTO.internal.StatusReadDTOInternal;
import com.travelapp.api.status.DTO.internal.StatusUpdateDTOInternal;
import com.travelapp.api.status.service.StatusServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

//Might consider using a global error handling device for handling all
//EntityNotFoundExceptions uniformly while maintaining

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusServiceImpl statusService;

    @GetMapping("/{statusId}")
    public ResponseEntity<StatusReadDTOInternal> getStatusById(@PathVariable Long statusId){
        try {
            StatusReadDTOInternal statusToGet = statusService.getStatus(statusId);
            return new ResponseEntity<>(statusToGet, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<StatusReadDTOInternal>> getStatusAll(){
        List<StatusReadDTOInternal> statuses = statusService.getAllStatus();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StatusReadDTOInternal> createStatus(@RequestBody StatusCreateDTOInternal statusCreateDTOInternal){
        StatusReadDTOInternal statusCreated = statusService.createStatus(statusCreateDTOInternal);
        return new ResponseEntity<>(statusCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<StatusReadDTOInternal> updateStatus(@RequestBody StatusUpdateDTOInternal statusUpdateDTOInternal){
        try {
            StatusReadDTOInternal statusUpdated = statusService.updateStatus(statusUpdateDTOInternal);
            return new ResponseEntity<>(statusUpdated, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{statusId}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long statusId){
        try {
            statusService.deleteStatus(statusId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
