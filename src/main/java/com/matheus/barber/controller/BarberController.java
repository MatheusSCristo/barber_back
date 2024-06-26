package com.matheus.barber.controller;

import com.matheus.barber.dto.Barber.BarberCreateDto;
import com.matheus.barber.dto.Barber.BarberResponseDto;
import com.matheus.barber.dto.Barber.BarberUpdateDto;
import com.matheus.barber.entity.Barber;
import com.matheus.barber.service.BarberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("barber")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @GetMapping
    public ResponseEntity<List<BarberResponseDto>> getAllBarbers(){
        return ResponseEntity.ok().body(barberService.getAllBarbers());
    }
    @GetMapping("{id}")
    public ResponseEntity<BarberResponseDto> getBarberById(@PathVariable UUID id){
        return ResponseEntity.ok().body(barberService.getBarberById(id));
    }

    @PostMapping
    public ResponseEntity<BarberResponseDto> createBarber(@RequestBody @Valid BarberCreateDto barberCreateDto){
        return ResponseEntity.ok().body(barberService.createBarber(barberCreateDto));

    }

    @PatchMapping("{id}")
    public ResponseEntity<BarberResponseDto> updateBarber(@RequestBody @Valid BarberUpdateDto barberUpdateDto,@PathVariable UUID id){
        return ResponseEntity.ok().body(barberService.updateBarber(barberUpdateDto,id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBarber(@PathVariable UUID id){
        barberService.deleteBarber(id);
        return ResponseEntity.ok().build();
    }





}
