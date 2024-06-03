package com.matheus.barber.dto.Scheduling;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

public record SchedulingUpdateDto(UUID barber_id, Long start_time) {
}
