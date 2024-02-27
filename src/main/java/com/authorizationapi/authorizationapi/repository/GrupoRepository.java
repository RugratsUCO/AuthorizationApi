package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.GrupoDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GrupoRepository extends ReactiveCrudRepository<GrupoDomain, String> {
}
