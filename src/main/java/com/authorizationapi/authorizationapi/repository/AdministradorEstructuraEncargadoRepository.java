package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdministradorEstructuraEncargadoRepository extends JpaRepository<AdministradorEstructuraEncargado, UUID> {
}
