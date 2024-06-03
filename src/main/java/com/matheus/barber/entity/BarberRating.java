package com.matheus.barber.entity;

import com.matheus.barber.dto.BarberRating.BarberRatingCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "barber_rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberRating {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Barber barber;
    private String name;
    private Integer rating;
    @Column(columnDefinition = "TEXT")
    private String text;
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt;

    public BarberRating(BarberRatingCreateDto barberRatingCreateDto){
        this.name=barberRatingCreateDto.name();
        this.rating= barberRatingCreateDto.rating();
        this.text=barberRatingCreateDto.text();
    }

}
