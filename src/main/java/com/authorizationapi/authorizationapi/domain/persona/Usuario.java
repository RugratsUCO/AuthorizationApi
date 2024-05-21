package com.authorizationapi.authorizationapi.domain.persona;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador",nullable = false)
    private UUID identificador;

    @Column(name = "\"tipoIdentificacion\"")
    private String tipoIdentificacion;

    @Column(name = "\"numeroIdentificacion\"")
    private String numeroIdentificacion;

    @Column(name = "\"primerNombre\"")
    private String primerNombre;

    @Column(name = "\"segundoNombre\"")
    private String segundoNombre;

    @Column(name = "\"primerApellido\"")
    private String primerApellido;

    @Column(name = "\"segundoApellido\"")
    private String segundoApellido;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "\"paisTelefono\"")
    private String paisTelefono;

    @Column(name = "\"numeroTelefono\"")
    private String numeroTelefono;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
