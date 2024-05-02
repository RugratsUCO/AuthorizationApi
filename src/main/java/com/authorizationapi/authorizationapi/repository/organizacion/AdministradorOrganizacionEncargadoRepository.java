package com.authorizationapi.authorizationapi.repository.organizacion;


import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdministradorOrganizacionEncargadoRepository extends JpaRepository<AdministradorOrganizacionEncargado, UUID> {
}
