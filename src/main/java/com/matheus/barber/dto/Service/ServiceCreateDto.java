package com.matheus.barber.dto.Service;

import com.matheus.barber.enums.ServiceType;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ServiceCreateDto(@NotNull ServiceType service_type, @NotNull Integer average_duration, @NotNull Double total, @NotNull UUID barber_shop_id) {
}
