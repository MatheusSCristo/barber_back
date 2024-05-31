package com.matheus.barber.controller;

import com.matheus.barber.dto.Service.ServiceCreateDto;
import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.dto.Service.ServiceUpdateDto;
import com.matheus.barber.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("findByBarberShopId/{id}")
    public ResponseEntity<List<ServiceResponseDto>> getServicesByBarberShopId(@PathVariable UUID id){
        return ResponseEntity.ok().body(serviceService.getServicesByBarberShopId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<ServiceResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(serviceService.getServiceById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDto> createService(@RequestBody @Valid ServiceCreateDto serviceCreateDto){
        return ResponseEntity.ok().body(serviceService.createService(serviceCreateDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ServiceResponseDto> updateService(@PathVariable UUID id,@RequestBody @Valid ServiceUpdateDto serviceUpdateDto) {
        return ResponseEntity.ok().body(serviceService.updateService(serviceUpdateDto,id));
    }

    public ResponseEntity<Void> updateService(@PathVariable UUID id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok().build();
    }
}
