package com.matheus.barber.service;

import com.matheus.barber.dto.Email.EmailSendDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class MessageService {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public MessageService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendReminderEmail(EmailSendDto emailSendDto)  {
        try {
        SimpleDateFormat entryFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        Date data = entryFormat.parse(emailSendDto.getTime().toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formatedDate = dateFormat.format(data);
        String formatedTime = timeFormat.format(data);
            MimeMessage message = mailSender.createMimeMessage();
            Context context = new Context();
            context.setVariable("barberShop", emailSendDto.getBarberShop());
            context.setVariable("username", emailSendDto.getUserName());
            context.setVariable("service", emailSendDto.getService().getService());
            context.setVariable("date", formatedDate);
            context.setVariable("time",formatedTime);
            context.setVariable("site", emailSendDto.getSite());
            String process = templateEngine.process("email.html", context);
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(emailSendDto.getUserEmail());
            helper.setFrom(new InternetAddress(username));
            helper.setSubject("Lembrança do seu agendamento");
            helper.setText(process, true);
            mailSender.send(message);
        } catch (MessagingException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


}
