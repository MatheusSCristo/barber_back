package com.matheus.barber.entity;

import com.matheus.barber.dto.Scheduling.SchedulingCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.Instant;
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

    public Scheduling(SchedulingCreateDto schedulingCreateDto) {
        this.startTime = Timestamp.from(Instant.ofEpochMilli(schedulingCreateDto.start_time()));
        this.booked = true;
        this.finished = false;
    }

}
