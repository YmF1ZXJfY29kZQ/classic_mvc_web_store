package com.example.managerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VinylRecord {
    private UUID id;
    private String artist;
    private String albumTitle;
    private String genre;
    private int year;
    private double price;
    List<VinylSong> songs;
}
