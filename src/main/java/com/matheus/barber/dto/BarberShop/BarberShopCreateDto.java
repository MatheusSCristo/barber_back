package com.matheus.barber.dto.BarberShop;

import com.matheus.barber.enums.SchedulesEnum;
import jakarta.annotation.Nonnull;

import java.util.List;

public record BarberShopCreateDto(@Nonnull String name, @Nonnull String cnpj, @Nonnull String email,
                                  @Nonnull String password, @Nonnull String bio, @Nonnull String contact_number,
                                  @Nonnull String location_number, @Nonnull String cep,
                                  @Nonnull List<String> images_url, String instagram_url,@Nonnull List<String> available_schedules) {
}
