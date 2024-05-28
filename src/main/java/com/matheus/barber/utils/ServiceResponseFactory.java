package com.matheus.barber.utils;

import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.entity.Service;

public class ServiceResponseFactory {

    public static final ServiceResponseDto get(Service service){
        return new ServiceResponseDto(service);
    }

}
