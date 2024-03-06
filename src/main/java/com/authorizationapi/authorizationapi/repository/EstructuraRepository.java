package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstructuraRepository extends JpaRepository<Estructura, UUID> {
}
