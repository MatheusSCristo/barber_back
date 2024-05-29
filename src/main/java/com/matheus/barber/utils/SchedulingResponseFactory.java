package com.matheus.barber.utils;

import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.Scheduling;

import java.util.ArrayList;
import java.util.List;

public class SchedulingResponseFactory {


    public static List<SchedulingResponseDto> getList(List<Scheduling> schedulings){
        List<SchedulingResponseDto> response=new ArrayList<>();
        for(Scheduling scheduling:schedulings){
            response.add(new SchedulingResponseDto(scheduling));
        }
        return response;
    }
}
