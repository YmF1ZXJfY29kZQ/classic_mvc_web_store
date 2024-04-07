package com.example.managerapp.service;

import com.example.managerapp.controller.payload.CreateVinylRecordPayload;
import com.example.managerapp.controller.payload.UpdateVinylRecordPayload;
import com.example.managerapp.entity.VinylRecord;
import com.example.managerapp.repository.VinylRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultVinylRecordService implements VinylRecordService {
    private final VinylRecordRepository vinylRecordRepository;

    @Override
    public List<VinylRecord> getAllRecords() {
        return vinylRecordRepository.getAllRecords();
    }

    @Override
    public VinylRecord createNewRecord(CreateVinylRecordPayload model) {
        return vinylRecordRepository.create(VinylRecord.builder()
                .id(UUID.randomUUID())
                .genre(model.genre())
                .year(model.year())
                .albumTitle(model.albumTitle())
                .songs(new ArrayList<>())
                .price(model.price())
                .artist(model.artist())
                .build());
    }

    @Override
    public Optional<VinylRecord> getRecord(UUID recordId) {
        return vinylRecordRepository.getRecord(recordId);
    }

    @Override
    public void updateRecord(UUID id, UpdateVinylRecordPayload updateVinylRecordPayload) {
        vinylRecordRepository.getRecord(id).ifPresentOrElse(val -> {
            val.setGenre(updateVinylRecordPayload.genre());
            val.setYear(updateVinylRecordPayload.year());
            val.setArtist(updateVinylRecordPayload.artist());
            val.setAlbumTitle(updateVinylRecordPayload.albumTitle());
            val.setPrice(updateVinylRecordPayload.price());
        }, () -> {
            throw new NoSuchElementException();
        });

    }

    @Override
    public void deleteRecord(UUID id) {
        this.vinylRecordRepository.delete(id);
    }
}
