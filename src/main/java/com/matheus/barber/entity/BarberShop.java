package com.matheus.barber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Column(name = "instagram_link")
    private String instagramLink;
    @Column(name = "images_url")
    private List<String> imagesUrl=new ArrayList<>();
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt;
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<Scheduling> schedulings=new ArrayList<>();
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<Barber> barbers=new ArrayList<>();
    @OneToMany(mappedBy = "barberShop",fetch = FetchType.EAGER)
    private List<BarberShopRatings> ratings=new ArrayList<>();


}
