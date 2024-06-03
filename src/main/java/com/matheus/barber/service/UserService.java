package com.matheus.barber.service;

import com.matheus.barber.dto.User.UserResponseDto;
import com.matheus.barber.dto.User.UserUpdateDto;
import com.matheus.barber.entity.Barber;
import com.matheus.barber.entity.BarberShop;
import com.matheus.barber.entity.User;
import com.matheus.barber.infra.exceptions.BarberNotFoundException;
import com.matheus.barber.infra.exceptions.BarberShopNotFoundException;
import com.matheus.barber.infra.exceptions.UserNotFoundException;
import com.matheus.barber.repository.BarberRepository;
import com.matheus.barber.repository.BarberShopRepository;
import com.matheus.barber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private BarberShopRepository barberShopRepository;


    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(item->new UserResponseDto(item)).toList();
    }

    public UserResponseDto getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException();
        return new UserResponseDto(user.get());
    }

    public List<UserResponseDto> getAllUsersByBarberShop(UUID id) {
        Optional<BarberShop> optionalBarberShop = barberShopRepository.findById(id);
        if (optionalBarberShop.isEmpty()) throw new BarberShopNotFoundException();
        return userRepository.findAllByBarberShop(optionalBarberShop.get().getId()).stream().map(item->new UserResponseDto(item)).toList();

    }

    public List<UserResponseDto> getAllUsersByBarber(UUID id) {
        Optional<Barber> optionalBarber = barberRepository.findById(id);
        if (optionalBarber.isEmpty()) throw new BarberNotFoundException();
        return userRepository.findAllByBarber(optionalBarber.get().getId()).stream().map(item->new UserResponseDto(item)).toList();
    }

    public void deleteUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException();
        userRepository.deleteById(id);
    }

    public UserResponseDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new UserNotFoundException();
        User user = userOptional.get();
        user.setName(userUpdateDto.name() != null ? userUpdateDto.name() : user.getName());
        user.setLastName(userUpdateDto.last_name() != null ? userUpdateDto.last_name() : user.getLastName());
        user.setCpf(userUpdateDto.cpf() != null ? userUpdateDto.cpf() : user.getCpf());
        user.setRole(userUpdateDto.role() != null ? userUpdateDto.role() : user.getRole());
        user.setSex(userUpdateDto.sex() != null ? userUpdateDto.sex() : user.getSex());
        user.setEmail(userUpdateDto.email() != null ? userUpdateDto.email() : user.getEmail());
        user.setPassword(userUpdateDto.password() != null ? userUpdateDto.password() : user.getPassword());
        user.setBirthDate(userUpdateDto.birth_date() != null ? userUpdateDto.birth_date() : user.getBirthDate());
        user.setPhoneNumber(userUpdateDto.phone_number() != null ? userUpdateDto.phone_number() : user.getPhoneNumber());
        userRepository.save(user);
        return new UserResponseDto(user);
    }



}
