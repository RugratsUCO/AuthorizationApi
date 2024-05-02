package com.authorizationapi.authorizationapi.repository.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GrupoRepository extends JpaRepository<Grupo, UUID> {
}
