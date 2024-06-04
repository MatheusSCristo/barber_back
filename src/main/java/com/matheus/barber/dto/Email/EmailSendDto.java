package com.matheus.barber.dto.Email;

import com.matheus.barber.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class EmailSendDto {
    private String userEmail;
    private Timestamp time;
    private String BarberShop;
    private String userName;
    private ServiceType service;
    private String site;
}
