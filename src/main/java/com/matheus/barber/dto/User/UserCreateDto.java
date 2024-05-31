package com.matheus.barber.dto.User;

import com.matheus.barber.enums.RoleEnum;
import com.matheus.barber.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record UserCreateDto(@NotBlank String name, @NotBlank String last_name, @NotBlank String email,
                            @NotBlank String password, @NotNull
                            Timestamp birth_date, @NotBlank String phone_number, @NotNull SexEnum sexEnum,
                            @NotBlank String cpf, @NotNull RoleEnum role) {
}
