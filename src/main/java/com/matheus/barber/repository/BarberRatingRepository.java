package com.matheus.barber.repository;

import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BarberRatingRepository extends JpaRepository<BarberRating, UUID> {

    public Optional<BarberRating> findByBarber(Barber barber);
}
