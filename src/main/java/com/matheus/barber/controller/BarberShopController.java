package com.matheus.barber.controller;

import com.matheus.barber.dto.BarberShop.BarberShopCreateDto;
import com.matheus.barber.dto.BarberShop.BarberShopResponseDto;
import com.matheus.barber.dto.BarberShop.BarberShopUpdateDto;
import com.matheus.barber.service.BarberShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("barbershop")
public class BarberShopController {

    @Autowired
    private BarberShopService barberShopService;

    @GetMapping
    public ResponseEntity<List<BarberShopResponseDto>> getAllBarberShops() {
        return ResponseEntity.ok().body(barberShopService.getAllBarberShops());
    }

    @GetMapping("{id}")
    public ResponseEntity<BarberShopResponseDto> getBarberShopById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(barberShopService.getBarberShopById(id));
    }

    @PostMapping
    public ResponseEntity<BarberShopResponseDto> createBarberShop(@RequestBody @Valid BarberShopCreateDto barberShopCreateDto) {
        return ResponseEntity.ok().body(barberShopService.createBarberShop(barberShopCreateDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<BarberShopResponseDto> updateBarberShop(@PathVariable UUID id, @RequestBody @Valid BarberShopUpdateDto barberShopUpdateDto) {
        return ResponseEntity.ok().body(barberShopService.updateBarberShop(id, barberShopUpdateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBarberShop(@PathVariable UUID id) {
        barberShopService.deleteBarberShop(id);
        return ResponseEntity.ok().build();
    }


}
