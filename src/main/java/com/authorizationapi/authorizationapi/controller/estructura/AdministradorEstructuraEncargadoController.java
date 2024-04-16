package com.authorizationapi.authorizationapi.controller.estructura;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import com.authorizationapi.authorizationapi.service.estructura.AdministradorEstructuraEncargadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("authorization/api/v1")
public final class AdministradorEstructuraEncargadoController {

    @Autowired
    private final AdministradorEstructuraEncargadoService service = new AdministradorEstructuraEncargadoService();

    @PostMapping("/administradorestructura")
    public ResponseEntity<Response<AdministradorEstructuraEncargado>> concederPermisos(@RequestBody AdministradorEstructuraEncargado administrador) {


        var statusCode = HttpStatus.OK;
        Response<AdministradorEstructuraEncargado> response = new Response<>();

        try {
            service.concederPermisos(administrador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_CREADO_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_CREADO_FINAL);

        }
        return new ResponseEntity<>(response, statusCode);
    }

    @GetMapping("/administradorestructura")
    public ResponseEntity<Response<AdministradorEstructuraEncargado>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<AdministradorEstructuraEncargado> response;

        try {
            List<String> messages = new ArrayList<>();
            List<AdministradorEstructuraEncargado> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_CONSULTADO_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_CONSULTADO_FINAL);
            }

            response = new Response<>(list, messages);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_CONSULTADO_INTERNO_FINAL);
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @PutMapping("/administradorestructura/{identificador}")
    public ResponseEntity<Response<AdministradorEstructuraEncargado>> cambiarEstado(@PathVariable UUID identificador, @RequestBody AdministradorEstructuraEncargado administrador) {

        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorEstructuraEncargado>();

        try {
            service.cambiarEstado(identificador, administrador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_ESTADO_EDITADO_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_ESTADO_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response, statusCode);
    }

    @DeleteMapping("/administradorestructura/{identificador}")
    public ResponseEntity<Response<AdministradorEstructuraEncargado>> eliminar(@RequestBody AdministradorEstructuraEncargado administrador) {

        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorEstructuraEncargado>();

        try {
            service.eliminar(administrador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_ELIMINADO_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_ELIMINADO_FINAL);
        }

        return new ResponseEntity<>(response, statusCode);
    }
}
