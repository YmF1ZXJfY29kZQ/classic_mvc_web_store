package com.example.managerapp.repository;

import com.example.managerapp.entity.VinylRecord;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryVinylRecordRepository implements VinylRecordRepository {

    private final List<VinylRecord> vinylRecords = Collections.synchronizedList(new ArrayList<>());

    InMemoryVinylRecordRepository() {
        for (int i = 1; i <= 10; i++) {
            VinylRecord vinylRecord = new VinylRecord();
            vinylRecord.setId(UUID.randomUUID());
            vinylRecord.setArtist("Artist " + i);
            vinylRecord.setAlbumTitle("Album " + i);
            vinylRecord.setGenre("Genre " + i);
            vinylRecord.setYear(2000 + i);
            vinylRecord.setPrice(10.99 * i);

            vinylRecords.add(vinylRecord);
        }
    }

    @Override
    public List<VinylRecord> getAllRecords() {
        return Collections.unmodifiableList(vinylRecords);
    }

    @Override
    public VinylRecord create(VinylRecord vinylRecord) {
        vinylRecords.add(vinylRecord);
        return vinylRecord;
    }

    @Override
    public Optional<VinylRecord> getRecord(UUID recordId) {
        return vinylRecords.stream().filter(val -> val.getId().equals(recordId)).findFirst();
    }

    @Override
    public void delete(UUID id) {
        this.vinylRecords.removeIf(vinylRecord -> Objects.equals(id, vinylRecord.getId()));
    }

}
