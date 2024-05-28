package com.matheus.barber.repository;

import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT DISTINCT u FROM User u JOIN u.schedulings s WHERE s.barber.id = :barberId")
    public List<User> findAllByBarber(@Param("barberId") Integer barberId);

    @Query("SELECT DISTINCT u FROM User u JOIN u.schedulings s WHERE s.barberShop.id= :barberShopId")
    public List<User> findAllByBarberShop(@Param("barberShopId") UUID BarberShopId);
}
