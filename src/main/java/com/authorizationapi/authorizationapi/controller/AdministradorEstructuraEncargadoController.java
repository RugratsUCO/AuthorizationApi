package com.authorizationapi.authorizationapi.controller;

import com.authorizationapi.authorizationapi.domain.AdministradorEstructuraEncargadoDomain;
import com.authorizationapi.authorizationapi.service.business.AdministradorEstructuraEncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class AdministradorEstructuraEncargadoController {

    @Autowired
    private AdministradorEstructuraEncargadoService administradorEstructuraService;

    @GetMapping("/administradororganizacionencargado")
    public Flux<AdministradorEstructuraEncargadoDomain> list() {

        return administradorEstructuraService.consultar();
    }
    @PostMapping("/administradororganizacionencargado")
    public Mono<AdministradorEstructuraEncargadoDomain> concederPermisos(@RequestBody AdministradorEstructuraEncargadoDomain admin) {
        return administradorEstructuraService.concederPermisos(admin);
    }

    @PutMapping("/administradororganizacionencargado")
    public Mono<AdministradorEstructuraEncargadoDomain> cambiarEstado(@RequestBody AdministradorEstructuraEncargadoDomain admin) {
        administradorEstructuraService.cambiarEstado(admin);
    }



}
