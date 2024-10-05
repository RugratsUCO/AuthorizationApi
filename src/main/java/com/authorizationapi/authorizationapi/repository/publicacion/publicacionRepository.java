package com.authorizationapi.authorizationapi.repository.publicacion;

import com.authorizationapi.authorizationapi.domain.publicacion.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface publicacionRepository extends JpaRepository<Publicacion, UUID> {
}
