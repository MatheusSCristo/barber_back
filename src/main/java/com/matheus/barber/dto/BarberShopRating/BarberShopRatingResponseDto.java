package com.matheus.barber.dto.BarberShopRating;

import com.matheus.barber.dto.BarberShop.BarberShopResponseDto;
import com.matheus.barber.entity.BarberRating;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.BarberShopRating;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
public class BarberShopRatingResponseDto {
    private UUID id;
    private UUID barberShopId;
    private String name;
    private Integer rating;
    private String text;
    private Timestamp createdAt;

    public BarberShopRatingResponseDto(BarberShopRating barberShopRating){
        this.id=barberShopRating.getId();
        this.barberShopId=barberShopRating.getBarberShop().getId();
        this.name=barberShopRating.getName();
        this.rating=barberShopRating.getRating();
        this.text=barberShopRating.getText();
        this.createdAt=barberShopRating.getCreatedAt();
    }
}
