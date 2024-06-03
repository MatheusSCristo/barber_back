package com.matheus.barber.controller;

import com.matheus.barber.dto.BarberRating.BarberRatingCreateDto;
import com.matheus.barber.dto.BarberRating.BarberRatingResponseDto;
import com.matheus.barber.dto.BarberShopRating.BarberShopRatingCreateDto;
import com.matheus.barber.dto.BarberShopRating.BarberShopRatingResponseDto;
import com.matheus.barber.service.BarberRatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rating/barber")
public class BarberRatingController {

    @Autowired
    private BarberRatingService barberRatingService;

    @GetMapping("{id}")
    public ResponseEntity<List<BarberRatingResponseDto>> getRatingsByBarberId(@PathVariable UUID id) {
        return ResponseEntity.ok().body(barberRatingService.getRatingsByBarberId(id));
    }

    @PostMapping
    public ResponseEntity<BarberRatingResponseDto> createRating(@RequestBody @Valid BarberRatingCreateDto barberRatingCreateDto) {
        return ResponseEntity.ok().body(barberRatingService.createRating(barberRatingCreateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable UUID id) {
        barberRatingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }



}
