package com.matheus.barber.entity;

import com.matheus.barber.dto.User.UserCreateDto;
import com.matheus.barber.enums.RoleEnum;
import com.matheus.barber.enums.SexEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "birth_date",nullable = false)
    private Timestamp birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;
    private SexEnum sex;
    private String cpf;
    private RoleEnum role;
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt;
    @OneToMany(mappedBy = "user")
    private List<Scheduling> schedulings=new ArrayList<>();

    public User(UserCreateDto userCreateDto,String hashedPassword){
        this.name=userCreateDto.name();
        this.lastName=userCreateDto.last_name();
        this.email=userCreateDto.email();
        this.password=hashedPassword;
        this.birthDate=userCreateDto.birth_date();
        this.phoneNumber=userCreateDto.phone_number();
        this.sex=userCreateDto.sex();
        this.cpf=userCreateDto.cpf();
        this.role=userCreateDto.role();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
