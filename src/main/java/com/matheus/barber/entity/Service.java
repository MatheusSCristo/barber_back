package com.matheus.barber.entity;

import com.matheus.barber.enums.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barber_shop_id")
    private BarberShop barberShop;
    @Column(name = "service_type")
    private ServiceType serviceType;
    @Column(name = "average_duration")
    private Integer averageDuration;
    private Double total;
    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<Scheduling> schedulings = new ArrayList<>();
}
