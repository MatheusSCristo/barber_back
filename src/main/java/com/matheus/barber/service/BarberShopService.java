package com.matheus.barber.service;

import com.matheus.barber.dto.BarberShop.BarberShopCreateDto;
import com.matheus.barber.dto.BarberShop.BarberShopResponseDto;
import com.matheus.barber.dto.BarberShop.BarberShopUpdateDto;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.infra.exceptions.BarberShopNotFoundException;
import com.matheus.barber.repository.BarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarberShopService {

    @Autowired
    private BarberShopRepository barberShopRepository;

    public List<BarberShopResponseDto> getAllBarberShops() {
        List<BarberShop> barberShops = barberShopRepository.findAll();
        return barberShops.stream().map(item -> new BarberShopResponseDto(item)).toList();
    }

    public BarberShopResponseDto getBarberShopById(UUID id) {
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(id);
        if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        return new BarberShopResponseDto(optionalBarberShop.get());
    }

    public BarberShopResponseDto createBarberShop(BarberShopCreateDto barberShopCreateDto) {
        BarberShop barberShop = new BarberShop(barberShopCreateDto);

        barberShopRepository.save(barberShop);
        return new BarberShopResponseDto(barberShop);
    }

    public BarberShopResponseDto updateBarberShop(UUID id, BarberShopUpdateDto barberShopUpdateDto) {
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(id);
        if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        BarberShop barberShop = optionalBarberShop.get();
        barberShop.setName(Optional.ofNullable(barberShopUpdateDto.name()).orElse(barberShop.getName()));
        barberShop.setCnpj(Optional.ofNullable(barberShopUpdateDto.cnpj()).orElse(barberShop.getCnpj()));
        barberShop.setEmail(Optional.ofNullable(barberShopUpdateDto.email()).orElse(barberShop.getEmail()));
        barberShop.setPassword(Optional.ofNullable(barberShopUpdateDto.password()).orElse(barberShop.getPassword()));
        barberShop.setBio(Optional.ofNullable(barberShopUpdateDto.bio()).orElse(barberShop.getBio()));
        barberShop.setContactNumber(Optional.ofNullable(barberShopUpdateDto.contact_number()).orElse(barberShop.getContactNumber()));
        barberShop.setLocationNumber(Optional.ofNullable(barberShopUpdateDto.location_number()).orElse(barberShop.getLocationNumber()));
        barberShop.setCep(Optional.ofNullable(barberShopUpdateDto.cep()).orElse(barberShop.getCep()));
        barberShop.setInstagramUrl(Optional.ofNullable(barberShopUpdateDto.instagram_url()).orElse(barberShop.getInstagramUrl()));
        barberShop.setImagesUrl(Optional.ofNullable(barberShopUpdateDto.images_url()).orElse(barberShop.getImagesUrl()));
        barberShopRepository.save(barberShop);
        return new BarberShopResponseDto(barberShop);
    }

    public void deleteBarberShop(UUID id) {
        barberShopRepository.deleteById(id);
    }


}
