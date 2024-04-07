package com.example.managerapp.controller.payload;

public record UpdateVinylRecordPayload(String artist, String albumTitle, String genre, int year, double price) {
}
