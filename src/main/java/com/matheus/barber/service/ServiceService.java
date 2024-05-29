package com.matheus.barber.service;

import com.matheus.barber.dto.Service.ServiceCreateDto;
import com.matheus.barber.dto.Service.ServiceResponseDto;
import com.matheus.barber.dto.Service.ServiceUpdateDto;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.infra.exceptions.BarberShopNotFoundException;
import com.matheus.barber.infra.exceptions.ServiceNotFoundException;
import com.matheus.barber.repository.BarberShopRepository;
import com.matheus.barber.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private BarberShopRepository barberShopRepository;

    public List<ServiceResponseDto> getServicesByBarberShopId(UUID id){
        Optional<BarberShop> optionalBarberShop=barberShopRepository.findById(id);
        if(optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        return serviceRepository.findAllByBarberShop(optionalBarberShop.get()).stream().map(item->new ServiceResponseDto(item)).toList();
    }

    public ServiceResponseDto getServiceById(UUID id){
        Optional<com.matheus.barber.entity.Service> optionalService=serviceRepository.findById(id);
        if(optionalService.isEmpty()) throw new ServiceNotFoundException();
        return new ServiceResponseDto(optionalService.get());
    }

    public ServiceResponseDto createService(ServiceCreateDto serviceCreateDto){
        com.matheus.barber.entity.Service service=new com.matheus.barber.entity.Service(serviceCreateDto);
        Optional<BarberShop> optionalBarberShop=barberShopRepository.findById(serviceCreateDto.barber_shop_id());
        if(optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        service.setBarberShop(optionalBarberShop.get());
        serviceRepository.save(service);
        return new ServiceResponseDto(service);
    }

    public ServiceResponseDto updateService(ServiceUpdateDto serviceUpdateDto,UUID id){
        Optional<com.matheus.barber.entity.Service> optionalService=serviceRepository.findById(id);
        if(optionalService.isEmpty()) throw new ServiceNotFoundException();
        com.matheus.barber.entity.Service service=optionalService.get();
        service.setServiceType(Optional.ofNullable(serviceUpdateDto.service_type()).orElse(service.getServiceType()));
        service.setTotal(Optional.ofNullable(serviceUpdateDto.total()).orElse(service.getTotal()));
        service.setAverageDuration(Optional.ofNullable(serviceUpdateDto.average_duration()).orElse(service.getAverageDuration()));
        serviceRepository.save(service);
        return new ServiceResponseDto(service);
    }


    public void deleteService(UUID id){
        Optional<com.matheus.barber.entity.Service> optionalService=serviceRepository.findById(id);
        if(optionalService.isEmpty()) throw new ServiceNotFoundException();
        serviceRepository.deleteById(id);
    }


}
