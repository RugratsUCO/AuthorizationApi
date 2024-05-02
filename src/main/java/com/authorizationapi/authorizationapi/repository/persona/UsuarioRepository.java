package com.authorizationapi.authorizationapi.repository.persona;

import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByUsername(String usuario);
}
