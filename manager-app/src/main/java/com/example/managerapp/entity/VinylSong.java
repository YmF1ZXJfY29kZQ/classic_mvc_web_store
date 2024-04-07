package com.example.managerapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VinylSong {
    private UUID id;
    private String artist;
    private String albumTitle;
    private Integer orderNumber;
    private String name;
}
