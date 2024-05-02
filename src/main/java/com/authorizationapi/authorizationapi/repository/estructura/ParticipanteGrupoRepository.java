package com.authorizationapi.authorizationapi.repository.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.ParticipanteGrupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipanteGrupoRepository extends JpaRepository<ParticipanteGrupo, UUID> {
}
