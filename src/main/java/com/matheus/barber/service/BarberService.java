package com.matheus.barber.service;

import com.matheus.barber.dto.Barber.BarberCreateDto;
import com.matheus.barber.dto.Barber.BarberResponseDto;
import com.matheus.barber.dto.Barber.BarberUpdateDto;
import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.infra.exceptions.BarberNotFoundException;
import com.matheus.barber.infra.exceptions.BarberShopNotFoundException;
import com.matheus.barber.repository.BarberRepository;
import com.matheus.barber.repository.BarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private BarberShopRepository barberShopRepository;


    public List<BarberResponseDto> getAllBarbers() {
        return barberRepository.findAll().stream().map(item-> new BarberResponseDto(item)).toList();
    }

    public BarberResponseDto getBarberById(Integer id) {
        Optional<Barber> optionalBarber = barberRepository.findById(id);
        if (optionalBarber.isEmpty()) throw new BarberNotFoundException();
        return new BarberResponseDto(optionalBarber.get());
    }

    public BarberResponseDto createBarber(BarberCreateDto barberCreateDto) {
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(barberCreateDto.barber_shop_id());
        if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        Barber barber = new Barber(barberCreateDto);
        barber.setBarberShop(optionalBarberShop.get());
        barberRepository.save(barber);
        return new BarberResponseDto(barber);
    }

    public BarberResponseDto updateBarber(BarberUpdateDto barberUpdateDto, Integer id) {
        Optional<Barber> optionalBarber = barberRepository.findById(id);
        if (optionalBarber.isEmpty()) throw new BarberNotFoundException();
        Barber barber = optionalBarber.get();
        barber.setName(Optional.ofNullable(barberUpdateDto.name()).orElse(barber.getName()));
        barber.setBio(Optional.ofNullable(barberUpdateDto.bio()).orElse(barber.getBio()));
        barber.setImageUrl(Optional.ofNullable(barberUpdateDto.image_url()).orElse(barber.getImageUrl()));
        if (barberUpdateDto.barber_shop_id() != null) {
            Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(barberUpdateDto.barber_shop_id());
            if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
            barber.setBarberShop(optionalBarberShop.get());
        }
        return new BarberResponseDto(optionalBarber.get());
    }

    public void deleteBarber(Integer id) {
        Optional<Barber> optionalBarber = barberRepository.findById(id);
        if (optionalBarber.isEmpty()) throw new BarberNotFoundException();
        barberRepository.deleteById(id);
    }




}
