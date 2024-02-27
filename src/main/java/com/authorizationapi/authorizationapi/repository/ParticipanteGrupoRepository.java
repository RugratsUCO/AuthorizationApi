package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.ParticipanteGrupoDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ParticipanteGrupoRepository extends ReactiveCrudRepository<ParticipanteGrupoDomain, String> {
}
