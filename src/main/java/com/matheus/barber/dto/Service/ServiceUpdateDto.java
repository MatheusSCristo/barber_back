package com.matheus.barber.dto.Service;

import com.matheus.barber.enums.ServiceType;
import jakarta.annotation.Nonnull;

public record ServiceUpdateDto(ServiceType service_type, Integer average_duration, Double total) {
}
