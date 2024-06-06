package com.matheus.barber.dto.Scheduling;

import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
public class SchedulingResponseDto {
    private UUID id;
    private UUID barberId;
    private UUID barberShopId;
    private UUID userId;
    private ServiceResponseDto service;
    private String startTime;
    private String endTime;
    private boolean booked;
    private boolean finished;

    public SchedulingResponseDto(Scheduling scheduling){
        this.id=scheduling.getId();
        this.barberId=scheduling.getBarber().getId();
        this.barberShopId=scheduling.getBarberShop().getId();
        this.userId=scheduling.getUser().getId();
        this.service= new ServiceResponseDto(scheduling.getService());
        ZoneId zoneIdUTCMinus3 = ZoneId.of("America/Sao_Paulo");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneIdUTCMinus3);
        Instant startInstant = scheduling.getStartTime().toInstant();
        this.startTime = formatter.format(startInstant);
        Instant endInstant = scheduling.getEndTime().toInstant();
        this.endTime = formatter.format(endInstant);
        this.booked=scheduling.isBooked();
        this.finished=scheduling.isFinished();
    }

}
