package com.matheus.barber.entity;

import com.matheus.barber.dto.BarberShop.BarberShopCreateDto;
import com.matheus.barber.enums.SchedulesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "barber_shop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberShop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String cnpj;
    private String email;
    private String password;
    @Column(columnDefinition = "TEXT")
    private String bio;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "location_number")
    private String locationNumber;
    private String cep;
    @Column(name = "instagram_url")
    private String instagramUrl;
    @Column(name = "images_url")
    private List<String> imagesUrl=new ArrayList<>();
    @CreatedDate
    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<Scheduling> schedulings=new ArrayList<>();
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<Barber> barbers=new ArrayList<>();
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<BarberShopRating> ratings=new ArrayList<>();
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<Service> services=new ArrayList<>();
    private List<SchedulesEnum>  schedules=new ArrayList<>();


    public BarberShop(BarberShopCreateDto barberShopCreateDto){
        this.name=barberShopCreateDto.name();
        this.cnpj=barberShopCreateDto.cnpj();
        this.email=barberShopCreateDto.email();
        this.password=barberShopCreateDto.password();
        this.bio=barberShopCreateDto.bio();
        this.contactNumber=barberShopCreateDto.contact_number();
        this.locationNumber=barberShopCreateDto.location_number();
        this.cep=barberShopCreateDto.cep();
        this.instagramUrl=barberShopCreateDto.instagram_url();
        this.imagesUrl=barberShopCreateDto.images_url();
        this.schedules=barberShopCreateDto.available_schedules().stream().map(item->SchedulesEnum.fromString(item)).toList();
    }




}
