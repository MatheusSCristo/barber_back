package com.matheus.barber.dto.User;

import com.matheus.barber.dto.Scheduling.SchedulingResponseDto;
import com.matheus.barber.entity.Scheduling;
import com.matheus.barber.entity.User;
import com.matheus.barber.enums.RoleEnum;
import com.matheus.barber.enums.SexEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserAccessResponseDto {

    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private Timestamp birthDate;
    private String phoneNumber;
    private SexEnum sex;
    private String cpf;
    private RoleEnum role;
    private Timestamp createdAt;
    private List<SchedulingResponseDto> schedulings=new ArrayList<>();
    private String accessToken;

    public UserAccessResponseDto(User user){
        this.id=user.getId();
        this.name=user.getName();
        this.lastName=user.getLastName();
        this.email=user.getEmail();
        this.birthDate=user.getBirthDate();
        this.phoneNumber=user.getPhoneNumber();
        this.sex=user.getSex();
        this.cpf=user.getCpf();
        this.role=user.getRole();
        this.createdAt=user.getCreatedAt();
        this.schedulings=user.getSchedulings().stream().map(SchedulingResponseDto::new).toList();

    }
}
