package com.authorizationapi.authorizationapi.controller;

import com.authorizationapi.authorizationapi.domain.AdministradorEstructuraEncargadoDomain;
import com.authorizationapi.authorizationapi.service.business.AdministradorOrganizacionEncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public final class AdministradorOrganizacionEncargadoController {

    @Autowired
    private AdministradorOrganizacionEncargadoService administradorOrganizacionService;

    @GetMapping("/administradororganizacionencargado")
    public Flux<AdministradorEstructuraEncargadoDomain> list() {

        return administradorOrganizacionService.consultar();
    }

}
