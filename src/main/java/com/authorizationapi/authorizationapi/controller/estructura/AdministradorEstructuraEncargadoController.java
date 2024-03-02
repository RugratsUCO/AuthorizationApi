package com.authorizationapi.authorizationapi.controller.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import com.authorizationapi.authorizationapi.service.estructura.AdministradorEstructuraEncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public final class AdministradorEstructuraEncargadoController {

    @Autowired
    private AdministradorEstructuraEncargadoService administradorEstructuraService;





}
