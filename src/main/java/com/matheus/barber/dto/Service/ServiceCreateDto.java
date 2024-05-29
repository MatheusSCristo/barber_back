package com.matheus.barber.dto.Service;

import com.matheus.barber.enums.ServiceType;
import jakarta.annotation.Nonnull;

import java.util.UUID;

public record ServiceCreateDto(@Nonnull ServiceType service_type,@Nonnull Integer average_duration,@Nonnull Double total,@Nonnull UUID barber_shop_id) {
}
