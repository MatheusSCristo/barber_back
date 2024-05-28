package com.matheus.barber.utils;

import com.matheus.barber.dto.User.UserResponseDto;
import com.matheus.barber.entity.User;

public class UserResponseFactory {

    public final static UserResponseDto get(User user){
        return new UserResponseDto(user);
    }
}
