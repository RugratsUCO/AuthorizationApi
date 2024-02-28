package com.authorizationapi.authorizationapi.service.business;

import com.authorizationapi.authorizationapi.domain.AdministradorEstructuraEncargadoDomain;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
public final class AdministradorEstructuraEncargadoService {

    public Mono<AdministradorEstructuraEncargadoDomain> concederPermisos(AdministradorEstructuraEncargadoDomain domain) {
        return repository.save(domain);
    }

    public void cambiarEstado(AdministradorEstructuraEncargadoDomain domain) {

    }

    public Flux<AdministradorEstructuraEncargadoDomain> consultar() {
        return repository.findAll();
    }

    public Mono<Void> eliminar(UUID domain) {
        return repository.findById(String.valueOf(domain)).flatMap(existingBook -> repository.deleteById(String.valueOf(domain)));
    }

  
}
