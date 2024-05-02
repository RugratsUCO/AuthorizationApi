package com.authorizationapi.authorizationapi.repository.organizacion;

import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizacionRepository extends JpaRepository<Organizacion, UUID> {
}
