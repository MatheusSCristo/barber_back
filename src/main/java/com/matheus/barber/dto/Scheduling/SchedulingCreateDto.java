package com.matheus.barber.dto.Scheduling;

import jakarta.annotation.Nonnull;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public record SchedulingCreateDto(@Nonnull UUID barber_shop_id, @Nonnull Integer barber_id, @Nonnull UUID user_id,
                                  @Nonnull UUID service_id, @Nonnull Long start_time) {
}
