package com.matheus.barber.service;

import com.matheus.barber.dto.Email.EmailSendDto;
import com.matheus.barber.entity.Scheduling;
import com.matheus.barber.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class EmailSchedulerService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Autowired
    private EmailConsumerService emailConsumerService;


    @Scheduled(fixedRate = 60000)
    public void sendScheduleReminderEmail() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        Date twoHoursLater = calendar.getTime();
        List<Scheduling> upcomingScheduling = schedulingRepository.findByStartTimeBetween(Timestamp.from(now.toInstant()), Timestamp.from(twoHoursLater.toInstant()));
        for (Scheduling scheduling : upcomingScheduling) {
            if (!scheduling.isReminderSent()) {
                EmailSendDto emailSendDto = new EmailSendDto(scheduling.getUser().getEmail(), scheduling.getStartTime(), scheduling.getBarberShop().getName(), scheduling.getUser().getName(), scheduling.getService().getServiceType());
                emailConsumerService.sendReminderEmail(emailSendDto);
                scheduling.setReminderSent(true);
                schedulingRepository.save(scheduling);
            }

        }


    }


}
