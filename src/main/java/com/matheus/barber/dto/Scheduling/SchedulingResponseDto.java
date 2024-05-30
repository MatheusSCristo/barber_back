package com.matheus.barber.dto.Scheduling;

import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class SchedulingResponseDto {
    private UUID id;
    private Integer barberId;
    private UUID barberShopId;
    private UUID userId;
    private ServiceResponseDto service;
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean booked;
    private boolean finished;

    public SchedulingResponseDto(Scheduling scheduling){
        this.id=scheduling.getId();
        this.barberId=scheduling.getBarber().getId();
        this.barberShopId=scheduling.getBarberShop().getId();
        this.userId=scheduling.getUser().getId();
        this.service= new ServiceResponseDto(scheduling.getService());
        this.startTime=scheduling.getStartTime();
        this.endTime=scheduling.getEndTime();
        this.booked=scheduling.isBooked();
        this.finished=scheduling.isFinished();
    }

}
