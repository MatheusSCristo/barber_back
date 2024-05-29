package com.matheus.barber.dto.Barber;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record BarberUpdateDto( String name,  String bio,  String image_url,  UUID barber_shop_id) {
}
