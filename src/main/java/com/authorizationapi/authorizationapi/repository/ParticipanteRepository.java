package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.ParticipanteDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ParticipanteRepository extends ReactiveCrudRepository<ParticipanteDomain, String> {
}
