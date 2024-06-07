package com.matheus.barber.service;

import com.matheus.barber.dto.User.UserAccessResponseDto;
import com.matheus.barber.dto.User.UserCreateDto;
import com.matheus.barber.dto.User.UserLoginDto;
import com.matheus.barber.entity.User;
import com.matheus.barber.infra.exceptions.CredentialsException;
import com.matheus.barber.infra.exceptions.EmailAlreadyRegisteredException;
import com.matheus.barber.infra.exceptions.UserNotFoundException;
import com.matheus.barber.infra.exceptions.security.AuthenticationException;
import com.matheus.barber.infra.security.JwtService;
import com.matheus.barber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public UserAccessResponseDto register(UserCreateDto userCreateDto) {
        Optional<User> userOptional = userRepository.findByEmail(userCreateDto.email().toLowerCase());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyRegisteredException();
        }
        String hashPassword = passwordEncoder.encode(userCreateDto.password());
        User user = new User(userCreateDto, hashPassword);
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        UserAccessResponseDto userAccessResponseDto=new UserAccessResponseDto(user);
        userAccessResponseDto.setAccessToken(token);
        return userAccessResponseDto;
    }


    public UserAccessResponseDto authenticate(UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.email().toLowerCase(), userLoginDto.password())
            );
            User user = (userRepository.findByEmail(userLoginDto.email().toLowerCase()).orElseThrow(UserNotFoundException::new));
            String token = jwtService.generateToken(user);
            UserAccessResponseDto userAccessResponseDto=new UserAccessResponseDto(user);
            userAccessResponseDto.setAccessToken(token);
            return userAccessResponseDto;
        } catch (InternalAuthenticationServiceException exception) {
            throw new AuthenticationException();
        } catch (BadCredentialsException exception) {
            throw new CredentialsException();
        }
    }
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
