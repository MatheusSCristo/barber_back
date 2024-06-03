package com.matheus.barber.dto.Scheduling;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public record SchedulingCreateDto(@NotNull UUID barber_shop_id, @NotNull UUID barber_id, @NotNull UUID user_id,
                                  @NotNull UUID service_id, @NotNull Long start_time) {
}
