package com.matheus.barber.repository;

import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.Scheduling;
import com.matheus.barber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

    public List<Scheduling> findByBarberShop(BarberShop barberShop);

    public List<Scheduling> findByUser(User user);

    List<Scheduling> findByStartTimeBetween(Timestamp startTime, Timestamp endTime);


}
