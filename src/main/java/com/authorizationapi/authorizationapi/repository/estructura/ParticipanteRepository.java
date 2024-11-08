package com.authorizationapi.authorizationapi.repository.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipanteRepository extends JpaRepository<Participante, UUID> {
    Participante findByPersona(Persona persona);
}
