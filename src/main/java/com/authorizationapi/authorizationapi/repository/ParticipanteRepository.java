package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipanteRepository extends JpaRepository<Participante, UUID> {
}
