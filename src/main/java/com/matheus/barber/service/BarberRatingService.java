package com.matheus.barber.service;

import com.matheus.barber.dto.Barber.BarberResponseDto;
import com.matheus.barber.dto.BarberRating.BarberRatingCreateDto;
import com.matheus.barber.dto.BarberRating.BarberRatingResponseDto;
import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberRating;
import com.matheus.barber.infra.exceptions.BarberNotFoundException;
import com.matheus.barber.infra.exceptions.BarberRatingNotFoundException;
import com.matheus.barber.repository.BarberRatingRepository;
import com.matheus.barber.repository.BarberRepository;
import com.matheus.barber.utils.FilterProhibitedWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarberRatingService {

    @Autowired
    private BarberRatingRepository barberRatingRepository;

    @Autowired
    private BarberRepository barberRepository;

    public List<BarberRatingResponseDto> getRatingsByBarberId(Integer id){
        Optional<Barber> barber=barberRepository.findById(id);
        if(barber.isEmpty()) throw new BarberNotFoundException();
        return barberRatingRepository.findByBarber(barber.get()).stream().map(item->new BarberRatingResponseDto(item)).toList();
    }

    public BarberRatingResponseDto createRating(BarberRatingCreateDto barberRatingCreateDto){
        FilterProhibitedWords.filter(barberRatingCreateDto.text());
        Optional<Barber> optionalBarber=barberRepository.findById(barberRatingCreateDto.barber_id());
        if(optionalBarber.isEmpty()) throw new BarberNotFoundException();
        BarberRating barberRating=new BarberRating(barberRatingCreateDto);
        barberRating.setBarber(optionalBarber.get());
        barberRatingRepository.save(barberRating);
        return new BarberRatingResponseDto(barberRating);
    }

    public void deleteRating(UUID id){
        Optional<BarberRating> barberRating=barberRatingRepository.findById(id);
        if(barberRating.isEmpty()) throw new BarberRatingNotFoundException();
        barberRatingRepository.deleteById(id);
    }


}
