package com.matheus.barber.dto.BarberRating;

import com.matheus.barber.entity.BarberRating;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class BarberRatingResponseDto {

    private UUID id;
    private UUID barberId;
    private String name;
    private Integer rating;
    private String text;
    private Timestamp createdAt;

    public BarberRatingResponseDto(BarberRating barberRating){
        this.id=barberRating.getId();
        this.barberId=barberRating.getBarber().getId();
        this.name=barberRating.getName();
        this.rating=barberRating.getRating();
        this.text=barberRating.getText();
        this.createdAt=barberRating.getCreatedAt();

    }


}

