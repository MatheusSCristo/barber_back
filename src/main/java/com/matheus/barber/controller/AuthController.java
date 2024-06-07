package com.matheus.barber.controller;

import com.matheus.barber.dto.User.UserAccessResponseDto;
import com.matheus.barber.dto.User.UserCreateDto;
import com.matheus.barber.dto.User.UserLoginDto;
import com.matheus.barber.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping(value ="/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAccessResponseDto> register(@RequestBody @Valid UserCreateDto userCreateDto) {
        return ResponseEntity.ok(authService.register(userCreateDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccessResponseDto> getUser(@RequestBody @Valid UserLoginDto userLoginDto) {
        return ResponseEntity.ok(authService.authenticate(userLoginDto));
    }




}
