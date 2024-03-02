package com.authorizationapi.authorizationapi.controller.organizacion;

import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import com.authorizationapi.authorizationapi.service.organizacion.AdministradorOrganizacionEncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public final class AdministradorOrganizacionEncargadoController {

    @Autowired
    private AdministradorOrganizacionEncargadoService administradorOrganizacionService;


}
