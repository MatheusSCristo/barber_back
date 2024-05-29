package com.matheus.barber.utils;

import com.matheus.barber.dto.BarberRating.BarberRatingResponseDto;
import com.matheus.barber.entity.BarberRating;

import java.util.ArrayList;
import java.util.List;

public class BarberRatingResponseFactory {

    public static List<BarberRatingResponseDto> getList(List<BarberRating> barberRatings){
        List<BarberRatingResponseDto> response=new ArrayList<>();
        for(BarberRating barberRating:barberRatings){
            response.add(new BarberRatingResponseDto(barberRating));
        }
        return response;
    }
}
