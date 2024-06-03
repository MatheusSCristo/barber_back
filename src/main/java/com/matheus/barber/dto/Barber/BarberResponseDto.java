package com.matheus.barber.dto.Barber;

import com.matheus.barber.dto.BarberRating.BarberRatingResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.Barber;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class BarberResponseDto {
    private UUID id;
    private String name;
    private String bio;
    private String imageUrl;
    private Timestamp createdAt;
    private UUID barberShopId;
    private List<SchedulingResponseDto> schedulings = new ArrayList<>();
    private List<BarberRatingResponseDto> ratings = new ArrayList<>();


    public BarberResponseDto(Barber barber) {

        this.id = barber.getId();
        this.name = barber.getName();
        this.bio = barber.getBio();
        this.imageUrl = barber.getImageUrl();
        this.createdAt = barber.getCreatedAt();
        this.barberShopId = barber.getBarberShop().getId();
        this.schedulings = barber.getSchedulings().stream().map(item->new SchedulingResponseDto(item)).toList();
        this.ratings = barber.getRatings().stream().map(item->new BarberRatingResponseDto(item)).toList();
    }
}
