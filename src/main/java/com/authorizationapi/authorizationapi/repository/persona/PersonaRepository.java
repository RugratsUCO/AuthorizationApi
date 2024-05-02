package com.authorizationapi.authorizationapi.repository.persona;

import com.authorizationapi.authorizationapi.domain.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonaRepository extends JpaRepository<Persona, UUID> {
}
