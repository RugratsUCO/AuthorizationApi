package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.PersonaDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonaRepository extends ReactiveCrudRepository<PersonaDomain, String> {
}
