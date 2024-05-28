package com.matheus.barber.repository;

import com.matheus.barber.entity.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarberShopRepository extends JpaRepository<BarberShop, UUID> {
}
