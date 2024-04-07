package com.example.managerapp.repository;

import com.example.managerapp.entity.VinylRecord;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VinylRecordRepository {
    List<VinylRecord> getAllRecords();

    VinylRecord create(VinylRecord vinylRecord);

    Optional<VinylRecord> getRecord(UUID recordId);

    void delete(UUID id);
}
