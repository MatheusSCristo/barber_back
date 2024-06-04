package com.matheus.barber.controller;

import com.matheus.barber.dto.Email.EmailSendDto;
import com.matheus.barber.enums.ServiceType;
import com.matheus.barber.service.MessageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("email")
    public ResponseEntity<Void> sendReminderEmail() {
        EmailSendDto emailSendDto = new EmailSendDto("matheus.cristo1@outlook.com", Timestamp.from(Instant.now()), "Barbearia", "Matheus", ServiceType.beard, "www.youtube.com");
        messageService.sendReminderEmail(emailSendDto);
        return ResponseEntity.ok().build();

    }

}
