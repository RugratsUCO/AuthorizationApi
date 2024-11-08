package com.authorizationapi.authorizationapi.repository.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdministradorEstructuraEncargadoRepository extends JpaRepository<AdministradorEstructuraEncargado, UUID> {
    AdministradorEstructuraEncargado findByPersona(Persona persona);
}
