package com.matheus.barber.entity;

import com.matheus.barber.dto.Barber.BarberCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "barber")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String bio;
    @Column(name = "image_url")
    private String imageUrl;
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "barber_shop_id")
    private BarberShop barberShop;
    @OneToMany(mappedBy = "barber",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Scheduling> schedulings=new ArrayList<>();
    @OneToMany(mappedBy = "barber")
    private List<BarberRating> ratings=new ArrayList<>();


    public Barber(BarberCreateDto barberCreateDto){
        this.name=barberCreateDto.name();
        this.bio=barberCreateDto.bio();
        this.imageUrl=barberCreateDto.image_url();
    }
}
