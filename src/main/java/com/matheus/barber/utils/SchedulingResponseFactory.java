package com.matheus.barber.utils;

import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.Scheduling;

public class SchedulingResponseFactory {


    public static final SchedulingResponseDto get(Scheduling scheduling){
        return new SchedulingResponseDto(scheduling);
    }
}
