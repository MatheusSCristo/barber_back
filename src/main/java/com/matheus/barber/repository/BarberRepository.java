package com.matheus.barber.repository;

import com.matheus.barber.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BarberRepository extends JpaRepository<Barber,Integer> {
}
