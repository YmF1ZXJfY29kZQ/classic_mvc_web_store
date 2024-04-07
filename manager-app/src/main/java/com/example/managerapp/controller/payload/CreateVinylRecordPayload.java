package com.example.managerapp.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateVinylRecordPayload(@NotNull
                                       @Size(min = 3, max = 50)
                                       String artist,
                                       @NotNull
                                       @Size(max = 100)
                                       String albumTitle,
                                       @NotNull
                                       @Size(max = 100)
                                       String genre,
                                       int year,
                                       double price) {

}
