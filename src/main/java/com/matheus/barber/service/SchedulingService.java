package com.matheus.barber.service;

import com.matheus.barber.dto.Barber.BarberResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingCreateDto;
import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.dto.Scheduling.SchedulingUpdateDto;
import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.entity.*;
import com.matheus.barber.enums.SchedulesEnum;
import com.matheus.barber.infra.exceptions.*;
import com.matheus.barber.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private BarberShopRepository barberShopRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BarberRepository barberRepository;


    public List<SchedulingResponseDto> getSchedulingByBarberShopId(UUID id) {
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(id);
        if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        return schedulingRepository.findByBarberShop(optionalBarberShop.get()).stream().map(item -> new SchedulingResponseDto(item)).toList();
    }


    public List<SchedulingResponseDto> getSchedulingByUserId(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException();
        return schedulingRepository.findByUser(optionalUser.get()).stream().map(item -> new SchedulingResponseDto(item)).toList();
    }

    public SchedulingResponseDto getSchedulingById(UUID id) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(id);
        if (optionalScheduling.isEmpty()) throw new SchedulingNotFoundException();
        return new SchedulingResponseDto(optionalScheduling.get());
    }

    public SchedulingResponseDto createScheduling(SchedulingCreateDto schedulingCreateDto) {
        Optional<User> optionalUser = userRepository.findById(schedulingCreateDto.user_id());
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(schedulingCreateDto.barber_shop_id());
        Optional<Barber> optionalBarber = barberRepository.findById(schedulingCreateDto.barber_id());
        Optional<com.matheus.barber.entity.Service> optionalService = serviceRepository.findById(schedulingCreateDto.service_id());
        if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        if (optionalUser.isEmpty()) throw new UserNotFoundException();
        if (optionalBarber.isEmpty()) throw new BarberNotFoundException();
        if (optionalService.isEmpty()) throw new ServiceNotFoundException();
        Scheduling scheduling = new Scheduling(schedulingCreateDto);
        scheduleValidations(optionalBarber.get(),optionalBarberShop.get(),schedulingCreateDto.start_time());
        scheduling.setService(optionalService.get());
        scheduling.setBarber(optionalBarber.get());
        scheduling.setBarberShop(optionalBarberShop.get());
        scheduling.setUser(optionalUser.get());
        scheduling.setEndTime(getEndTime(schedulingCreateDto.start_time(), optionalService.get().getAverageDuration()));
        schedulingRepository.save(scheduling);
        return new SchedulingResponseDto(scheduling);
    }

    public SchedulingResponseDto updateScheduling(UUID id, SchedulingUpdateDto schedulingUpdateDto) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(id);
        if (optionalScheduling.isEmpty()) throw new SchedulingNotFoundException();
        Scheduling scheduling = optionalScheduling.get();
        if (schedulingUpdateDto.start_time() != null) {
            validateDate(schedulingUpdateDto.start_time());
            scheduling.setStartTime(Timestamp.from(Instant.ofEpochMilli(schedulingUpdateDto.start_time())));
            scheduling.setEndTime(getEndTime( scheduling.getStartTime().getTime(), optionalScheduling.get().getService().getAverageDuration()));
        }
        if (schedulingUpdateDto.barber_id() != null) {
            Optional<Barber> optionalBarber = barberRepository.findById(schedulingUpdateDto.barber_id());
            if (optionalBarber.isEmpty()) throw new BarberNotFoundException();
            validateBarber(optionalBarber.get(),scheduling.getBarberShop());
            scheduling.setBarber(optionalBarber.get());
        }
        schedulingRepository.save(scheduling);
        return new SchedulingResponseDto(optionalScheduling.get());
    }

    public void deleteScheduling(UUID id) {
        Optional<Scheduling> optionalScheduling = schedulingRepository.findById(id);
        if (optionalScheduling.isEmpty()) throw new SchedulingNotFoundException();
        schedulingRepository.deleteById(id);
    }

    private Timestamp getEndTime(Long startTime, Integer serviceDuration) {
        long time = startTime + serviceDuration*60*1000;
        return Timestamp.from(new Date(time).toInstant());
    }

    private void validateDate(Long date) {
        if (new Date(date).before(new Date())) throw new DateIsNotValidException();
    }

    private void validateAvailableTime(Long date,BarberShop barberShop){
        String scheduledHour=new Date(date).toString().substring(11,16);
        if(!barberShop.getSchedules().contains(SchedulesEnum.fromString(scheduledHour))){
            throw new ScheduleTimeNotAvailableException();
        }
    }

    private void validateBarber(Barber barber,BarberShop barberShop){
        if(barberShop.getBarbers().contains(barber)) throw new BarberNotAssociatedToBarberShopException();
    }

    private void scheduleValidations(Barber barber,BarberShop barberShop,Long date){
        validateDate(date);
        validateAvailableTime(date,barberShop);
        validateBarber(barber,barberShop);
    }




}
