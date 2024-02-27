package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.EstadoDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EstadoRepository extends ReactiveCrudRepository<EstadoDomain, String> {
}
