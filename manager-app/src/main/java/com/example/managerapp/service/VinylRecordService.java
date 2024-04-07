package com.example.managerapp.service;

import com.example.managerapp.controller.payload.CreateVinylRecordPayload;
import com.example.managerapp.controller.payload.UpdateVinylRecordPayload;
import com.example.managerapp.entity.VinylRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VinylRecordService {
    List<VinylRecord> getAllRecords();

    VinylRecord createNewRecord(CreateVinylRecordPayload createVinylRecordPayload);

    Optional<VinylRecord> getRecord(UUID recordId);

    void updateRecord(UUID id, UpdateVinylRecordPayload updateVinylRecordPayload);

    void deleteRecord(UUID id);
}
