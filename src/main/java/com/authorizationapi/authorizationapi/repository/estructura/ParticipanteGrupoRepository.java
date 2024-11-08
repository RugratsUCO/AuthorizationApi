package com.authorizationapi.authorizationapi.repository.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import com.authorizationapi.authorizationapi.domain.estructura.ParticipanteGrupo;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipanteGrupoRepository extends JpaRepository<ParticipanteGrupo, UUID> {
    ParticipanteGrupo findByParticipante(Participante participante);
}
