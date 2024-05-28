package com.matheus.barber.dto.User;

import com.matheus.barber.enums.RoleEnum;
import com.matheus.barber.enums.SexEnum;
import jakarta.annotation.Nonnull;

import java.sql.Timestamp;

public record UserCreateDto(@Nonnull String name, @Nonnull String last_name, @Nonnull String email,
                            @Nonnull String password, @Nonnull
                            Timestamp birth_date, @Nonnull String phone_number, @Nonnull SexEnum sexEnum,
                            @Nonnull String cpf, @Nonnull
                            RoleEnum role) {
}
