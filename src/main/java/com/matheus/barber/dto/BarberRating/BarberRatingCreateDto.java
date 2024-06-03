package com.matheus.barber.dto.BarberRating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BarberRatingCreateDto(@NotNull Integer barber_id,@NotBlank String name,@NotNull Integer rating,String text) {
}
