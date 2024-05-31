package com.matheus.barber.service;

import com.matheus.barber.dto.BarberShop.BarberShopResponseDto;
import com.matheus.barber.dto.BarberShopRating.BarberShopRatingCreateDto;
import com.matheus.barber.dto.BarberShopRating.BarberShopRatingResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.BarberShopRating;
import com.matheus.barber.enums.ProhibitedWordsEnum;
import com.matheus.barber.infra.exceptions.BarberShopNotFoundException;
import com.matheus.barber.infra.exceptions.BarberShopRatingNotValidException;
import com.matheus.barber.infra.exceptions.TextHasProhibitedWordsException;
import com.matheus.barber.repository.BarberShopRatingRepository;
import com.matheus.barber.repository.BarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BarberShopRatingService {

    @Autowired
    private BarberShopRatingRepository barberShopRatingRepository;

    @Autowired
    private BarberShopRepository barberShopRepository;

    public BarberShopRatingResponseDto createRating(BarberShopRatingCreateDto barberShopRatingCreateDto){
        filterProhibitedWords(barberShopRatingCreateDto.text());
        BarberShopRating barberShopRating=new BarberShopRating(barberShopRatingCreateDto);
        Optional<BarberShop> optionalBarberShop=barberShopRepository.findById(barberShopRatingCreateDto.barber_shop_id());
        if(optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        barberShopRating.setBarberShop(optionalBarberShop.get());
        barberShopRatingRepository.save(barberShopRating);
        return new BarberShopRatingResponseDto(barberShopRating);
    }

    public void deleteRating(Integer id){
        Optional<BarberShopRating> optionalBarberShopRating=barberShopRatingRepository.findById(id);
        if(optionalBarberShopRating.isEmpty()) throw new BarberShopRatingNotValidException();
        barberShopRatingRepository.deleteById(id);
    }

    public List<BarberShopRatingResponseDto> getRatingsByBarberShopId(UUID id){
        Optional<BarberShop> optionalBarberShop=barberShopRepository.findById(id);
        if(optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        BarberShop barberShop=optionalBarberShop.get();
        return barberShop.getRatings().stream().map(item->new BarberShopRatingResponseDto(item)).toList();
    }


    private void filterProhibitedWords(String text){
        String[] words=text.split(" ");
       Set<String> prohibitedWordsSet = EnumSet.allOf(ProhibitedWordsEnum.class).stream()
                .map(ProhibitedWordsEnum::getWord)
                .collect(Collectors.toSet());
        for (String word:words){
            if(prohibitedWordsSet.contains(word.toLowerCase())) throw new TextHasProhibitedWordsException();
        }
    }


}
