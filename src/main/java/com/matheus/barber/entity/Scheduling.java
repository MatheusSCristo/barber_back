package com.matheus.barber.entity;

import com.matheus.barber.dto.Scheduling.SchedulingCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TimeZoneColumn;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "scheduling")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;
    @ManyToOne
    @JoinColumn(name = "barber_shop_id")
    private BarberShop barberShop;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    @Column(name = "booked")
    private boolean booked;
    @Column(name = "finished")
    private boolean finished;
    @Column(name ="reminder_sent")
    private boolean reminderSent;

    public Scheduling(SchedulingCreateDto schedulingCreateDto) {
        ZoneId zoneIdUTCMinus3 = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTimeUTCMinus3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(schedulingCreateDto.start_time()), zoneIdUTCMinus3);
        this.startTime = Timestamp.valueOf(localDateTimeUTCMinus3);
        this.booked = true;
        this.finished = false;
        this.reminderSent=true;
    }

}
