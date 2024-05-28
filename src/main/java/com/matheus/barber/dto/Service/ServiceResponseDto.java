package com.matheus.barber.dto.Service;

import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.Scheduling;
import com.matheus.barber.entity.Service;
import com.matheus.barber.enums.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ServiceResponseDto {
    private UUID id;
    private UUID barberShopId;
    private ServiceType serviceType;
    private Integer averageDuration;
    private Double total;

    public ServiceResponseDto(Service service){
        this.id=service.getId();
        this.averageDuration= service.getAverageDuration();
        this.barberShopId=service.getBarberShop().getId();
        this.serviceType=service.getServiceType();
        this.total=service.getTotal();
    }
}
