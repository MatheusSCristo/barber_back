package com.matheus.barber.dto.User;

import com.matheus.barber.enums.RoleEnum;
import com.matheus.barber.enums.SexEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Timestamp;

public record UserCreateDto(@NotBlank String name, @NotBlank String last_name, @NotBlank @Email String email,
                            @NotBlank @Min(6) String password, @NotNull Timestamp birth_date, @NotBlank String phone_number,
                            @NotNull SexEnum sex, @NotBlank @CPF String cpf, @NotNull RoleEnum role) {
}
