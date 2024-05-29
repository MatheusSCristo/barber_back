package com.matheus.barber.repository;

import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {

    public List<Service> findAllByBarberShop(BarberShop barberShop);
}
