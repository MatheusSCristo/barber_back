package com.matheus.barber.dto.BarberShop;

import com.matheus.barber.dto.Barber.BarberResponseDto;
import com.matheus.barber.dto.BarberShopRating.BarberShopRatingResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.BarberShop;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class BarberShopResponseDto {
    private UUID id;
    private String name;
    private String cnpj;
    private String email;
    private String bio;
    private String contactNumber;
    private String locationNumber;
    private String cep;
    private String instagramUrl;
    private List<String> imagesUrl=new ArrayList<>();
    private Timestamp createdAt;
    private List<SchedulingResponseDto> schedulings=new ArrayList<>();
    private List<BarberResponseDto> barbers=new ArrayList<>();
    private List<BarberShopRatingResponseDto> ratings=new ArrayList<>();

    public BarberShopResponseDto(BarberShop barberShop){
        this.id=barberShop.getId();
        this.name=barberShop.getName();
        this.cnpj=barberShop.getCnpj();
        this.email=barberShop.getEmail();
        this.bio=barberShop.getBio();
        this.contactNumber=barberShop.getContactNumber();
        this.locationNumber=barberShop.getLocationNumber();
        this.cep=barberShop.getCep();
        this.instagramUrl=barberShop.getInstagramUrl();
        this.imagesUrl=barberShop.getImagesUrl();
        this.schedulings= barberShop.getSchedulings().stream().map(item->new SchedulingResponseDto(item)).toList();
        this.barbers= barberShop.getBarbers().stream().map(item->new BarberResponseDto(item)).toList();
        this.ratings= barberShop.getRatings().stream().map(item->new BarberShopRatingResponseDto(item)).toList();
        this.createdAt=barberShop.getCreatedAt();
    }
}

