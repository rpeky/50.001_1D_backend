package com.travelapp.api.status.service;

import com.travelapp.api.status.DTO.internal.StatusCreateDTOInternal;
import com.travelapp.api.status.DTO.internal.StatusReadDTOInternal;
import com.travelapp.api.status.DTO.internal.StatusUpdateDTOInternal;

import java.util.List;

public interface StatusService {
    StatusReadDTOInternal getStatus(Long statusId);
    StatusReadDTOInternal createStatus(StatusCreateDTOInternal statusCreateDTOInternal);
    StatusReadDTOInternal updateStatus(StatusUpdateDTOInternal statusUpdateDTOInternal);
    void deleteStatus(Long statusId);
    List<StatusReadDTOInternal> getAllStatus();
}
