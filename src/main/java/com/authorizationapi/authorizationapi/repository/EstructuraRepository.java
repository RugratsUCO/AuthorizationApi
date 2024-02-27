package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.EstructuraDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EstructuraRepository extends ReactiveCrudRepository<EstructuraDomain, String> {
}
