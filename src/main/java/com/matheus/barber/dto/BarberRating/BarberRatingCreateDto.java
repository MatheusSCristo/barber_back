package com.matheus.barber.dto.BarberRating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BarberRatingCreateDto(@NotNull UUID barber_id, @NotBlank String name, @NotNull Integer rating, String text) {
}
