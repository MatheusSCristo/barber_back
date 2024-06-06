package com.matheus.barber.service;

import com.matheus.barber.dto.Email.EmailSendDto;
import com.matheus.barber.entity.Scheduling;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailConsumerService {


    @Value("${spring.mail.username}")
    private String username;

    @Value("${site.url}")
    private String siteUrl;

    private static JavaMailSender mailSender;
    private static SpringTemplateEngine templateEngine;

    public EmailConsumerService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendScheduledEmail(Scheduling scheduling) {
        EmailSendDto emailSendDto = new EmailSendDto(scheduling.getUser().getEmail(), scheduling.getStartTime(), scheduling.getBarberShop().getName(), scheduling.getUser().getName(), scheduling.getService().getServiceType());
        try {
            SimpleDateFormat entryFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
            Date data = entryFormat.parse(emailSendDto.getTime().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedDate = dateFormat.format(data);
            String formattedTime = timeFormat.format(data);
            MimeMessage message = mailSender.createMimeMessage();
            Context context = new Context();
            context.setVariable("barberShop", emailSendDto.getBarberShop());
            context.setVariable("username", emailSendDto.getUserName());
            context.setVariable("service", emailSendDto.getService().getService());
            context.setVariable("date", formattedDate);
            context.setVariable("time", formattedTime);
            context.setVariable("site", siteUrl);
            String process = templateEngine.process("scheduleEmail.html", context);
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(emailSendDto.getUserEmail());
            helper.setFrom(new InternetAddress(username));
            helper.setSubject("Confirmação do seu agendamento");
            helper.setText(process, true);
            mailSender.send(message);
        } catch (MessagingException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendReminderEmail(EmailSendDto emailSendDto) {
        try {
            SimpleDateFormat entryFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
            Date data = entryFormat.parse(emailSendDto.getTime().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedDate = dateFormat.format(data);
            String formattedTime = timeFormat.format(data);
            MimeMessage message = mailSender.createMimeMessage();
            Context context = new Context();
            context.setVariable("barberShop", emailSendDto.getBarberShop());
            context.setVariable("username", emailSendDto.getUserName());
            context.setVariable("service", emailSendDto.getService().getService());
            context.setVariable("date", formattedDate);
            context.setVariable("time", formattedTime);
            context.setVariable("site", siteUrl);
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
