package com.matheus.barber.dto.BarberShopRating;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BarberShopRatingCreateDto(@NotNull UUID barber_shop_id, String name, @NotNull @Min(1) @Max(5) Integer rating, String text) {
}
