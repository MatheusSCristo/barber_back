package com.matheus.barber.dto.Barber;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record BarberCreateDto(@Nonnull String name, @Nonnull String bio, @Nonnull String image_url, @Nonnull UUID barber_shop_id) {
}
