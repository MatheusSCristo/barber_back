package com.matheus.barber.dto.Barber;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BarberCreateDto(@NotBlank String name, @NotBlank String bio, @NotBlank String image_url, @NotNull UUID barber_shop_id) {
}
