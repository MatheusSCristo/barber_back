package com.matheus.barber.dto.BarberShop;

import com.matheus.barber.enums.SchedulesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BarberShopCreateDto(@NotBlank String name, @NotBlank String cnpj, @NotBlank String email,
                                  @NotBlank String password, @NotBlank String bio, @NotBlank String contact_number,
                                  @NotBlank String location_number, @NotBlank String cep,
                                  @NotNull List<String> images_url, @NotBlank String profile_image,
                                  String instagram_url, @NotNull List<String> available_schedules) {
}
