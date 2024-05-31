package com.matheus.barber.controller;

import com.matheus.barber.dto.BarberShopRating.BarberShopRatingCreateDto;
import com.matheus.barber.dto.BarberShopRating.BarberShopRatingResponseDto;
import com.matheus.barber.service.BarberShopRatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rating/barberShop")
public class BarberShopRatingController {

    @Autowired
    private BarberShopRatingService barberShopRatingService;

    @PostMapping
    public ResponseEntity<BarberShopRatingResponseDto> createRating(@RequestBody @Valid  BarberShopRatingCreateDto barberShopRatingCreateDto) {
        return ResponseEntity.ok().body(barberShopRatingService.createRating(barberShopRatingCreateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
        barberShopRatingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<List<BarberShopRatingResponseDto>> getRatingsByBarberShopId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(barberShopRatingService.getRatingsByBarberShopId(id));
    }




}
