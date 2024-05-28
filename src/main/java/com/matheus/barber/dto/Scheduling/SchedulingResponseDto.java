package com.matheus.barber.dto.Scheduling;

import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.entity.*;
import com.matheus.barber.utils.ServiceResponseFactory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingResponseDto {
    private Integer id;
    private Integer barberId;
    private UUID barberShopId;
    private UUID userId;
    private ServiceResponseDto service;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp date;
    private boolean booked;
    private boolean finished;

    public SchedulingResponseDto(Scheduling scheduling){
        this.id=scheduling.getId();
        this.barberId=scheduling.getBarber().getId();
        this.barberShopId=scheduling.getBarberShop().getId();
        this.userId=scheduling.getUser().getId();
        this.service= ServiceResponseFactory.get(scheduling.getService());
        this.startTime=scheduling.getStartTime();
        this.endTime=scheduling.getEndTime();
        this.date=scheduling.getDate();
        this.booked=scheduling.isBooked();
        this.finished=scheduling.isFinished();
    }

}
