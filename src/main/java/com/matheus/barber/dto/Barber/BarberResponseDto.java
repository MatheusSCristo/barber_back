package com.matheus.barber.dto.Barber;

import com.matheus.barber.dto.BarberRating.BarberRatingResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberRating;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.Scheduling;
import com.matheus.barber.utils.BarberRatingResponseFactory;
import com.matheus.barber.utils.SchedulingResponseFactory;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class BarberResponseDto {
    private Integer id;
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
        this.schedulings = SchedulingResponseFactory.getList(barber.getSchedulings());
        this.ratings = BarberRatingResponseFactory.getList(barber.getRatings());
    }
}
