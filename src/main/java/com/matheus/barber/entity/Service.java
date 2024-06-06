package com.matheus.barber.entity;

import com.matheus.barber.dto.Service.ServiceCreateDto;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barber_shop_id")
    private BarberShop barberShop;
    @Column(name = "service_type")
    private ServiceType serviceType;
    @Column(name = "average_duration")
    private Integer averageDuration;
    private Double total;
    @OneToMany(mappedBy = "service")
    private List<Scheduling> schedulings = new ArrayList<>();

    public Service(ServiceCreateDto serviceCreateDto){
        this.serviceType=serviceCreateDto.service_type();
        this.averageDuration=serviceCreateDto.average_duration();
        this.total=serviceCreateDto.total();
    }
}
