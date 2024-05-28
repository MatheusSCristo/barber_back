package com.matheus.barber.dto.User;

import com.matheus.barber.enums.RoleEnum;
import com.matheus.barber.enums.SexEnum;
import jakarta.annotation.Nonnull;

import java.sql.Timestamp;

public record UserUpdateDto(String name, String last_name, String email,
                            String password,
                            Timestamp birth_date, String phone_number, SexEnum sex,
                            String cpf,
                            RoleEnum role) {
}
