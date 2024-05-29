package com.matheus.barber.dto.BarberShop;

import java.util.List;

public record BarberShopUpdateDto(String name, String cnpj, String email,
                                  String password, String bio, String contact_number,
                                  String location_number, String cep,
                                  List<String> images_url,String instagram_url) {
}
