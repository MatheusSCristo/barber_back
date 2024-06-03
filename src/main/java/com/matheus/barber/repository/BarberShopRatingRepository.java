package com.matheus.barber.repository;

import com.matheus.barber.entity.BarberShopRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarberShopRatingRepository extends JpaRepository<BarberShopRating, UUID> {
}
