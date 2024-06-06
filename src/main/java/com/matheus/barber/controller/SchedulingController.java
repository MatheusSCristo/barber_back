package com.matheus.barber.controller;

import com.matheus.barber.dto.Scheduling.SchedulingCreateDto;
import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingUpdateDto;
import com.matheus.barber.service.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("byBarberShop/{id}")
    public ResponseEntity<List<SchedulingResponseDto>> getSchedulingByBarberShopId(@PathVariable UUID id){
        return ResponseEntity.ok().body(schedulingService.getSchedulingByBarberShopId(id));
    }

    @GetMapping("byUser/{id}")
    public ResponseEntity<List<SchedulingResponseDto>> getSchedulingByUserId(@PathVariable UUID id){
        return ResponseEntity.ok().body(schedulingService.getSchedulingByUserId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<SchedulingResponseDto> getSchedulingById(@PathVariable UUID id){
        return ResponseEntity.ok().body(schedulingService.getSchedulingById(id));
    }

    @PostMapping
    public ResponseEntity<SchedulingResponseDto> createScheduling(@RequestBody @Valid SchedulingCreateDto schedulingCreateDto){
        return ResponseEntity.ok().body(schedulingService.createScheduling(schedulingCreateDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<SchedulingResponseDto> updateScheduling(@PathVariable UUID id,@RequestBody @Valid SchedulingUpdateDto schedulingUpdateDto){
        return ResponseEntity.ok().body(schedulingService.updateScheduling(id,schedulingUpdateDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable UUID id){
        schedulingService.deleteScheduling(id);
        return ResponseEntity.ok().build();
    }
}
