package com.matheus.barber.controller;

import com.matheus.barber.dto.User.UserResponseDto;
import com.matheus.barber.dto.User.UserUpdateDto;
import com.matheus.barber.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("findByBarberShop/{id}")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByBarberShop(@PathVariable UUID id) {
        List<UserResponseDto> users = userService.getAllUsersByBarberShop(id);
        return ResponseEntity.ok().body(users);
    }


    @GetMapping("findByBarber/{id}")
    public ResponseEntity<List<UserResponseDto>> getAllUsersByBarber(@PathVariable Integer id) {
        List<UserResponseDto> users = userService.getAllUsersByBarber(id);
        return ResponseEntity.ok().body(users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<UserResponseDto>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateDto userUpdateDto){
        UserResponseDto user=userService.updateUser(id,userUpdateDto);
        return ResponseEntity.ok().body(user);
    }



}
