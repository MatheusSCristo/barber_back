package com.matheus.barber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "barber_shop_ratings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberShopRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private BarberShop barberShop;
    private String name;
    private Integer rating;
    @Column(columnDefinition = "TEXT")
    private String text;
    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;

}
