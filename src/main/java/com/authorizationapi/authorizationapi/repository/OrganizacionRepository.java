package com.authorizationapi.authorizationapi.repository;

import com.authorizationapi.authorizationapi.domain.OrganizacionDomain;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrganizacionRepository extends ReactiveCrudRepository<OrganizacionDomain, String> {
}
