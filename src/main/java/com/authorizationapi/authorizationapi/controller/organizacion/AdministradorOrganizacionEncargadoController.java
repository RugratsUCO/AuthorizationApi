package com.authorizationapi.authorizationapi.controller.organizacion;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.service.organizacion.AdministradorOrganizacionEncargadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("authorization/api/v1")
public final class AdministradorOrganizacionEncargadoController {

    @Autowired
    private final AdministradorOrganizacionEncargadoService service = new AdministradorOrganizacionEncargadoService();

    private final Logger log = LoggerFactory.getLogger(AdministradorOrganizacionEncargadoController.class);


    @PostMapping("/administradororganizacion")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> concederPermisos(@RequestBody AdministradorOrganizacionEncargado administrador) {


        var statusCode = HttpStatus.OK;
        Response<AdministradorOrganizacionEncargado> response = new Response<>();

        try {
            service.concederPermisos(administrador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_CREADO_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_CREADO_FINAL);

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/administradororganizacion")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<AdministradorOrganizacionEncargado> response;

        try {
            List<String> messages = new ArrayList<>();
            List<AdministradorOrganizacionEncargado> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_CONSULTADO_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_CONSULTADO_FINAL);
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_CONSULTADO_INTERNO_FINAL);
            log.error(exception.getMessage());
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/administradororganizacion/{identificador}")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> cambiarEstado(@PathVariable UUID identificador, @RequestBody AdministradorOrganizacionEncargado administrador) {

        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorOrganizacionEncargado>();

        try {
            service.cambiarEstado(identificador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_ESTADO_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_ESTADO_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @PatchMapping("/administradororganizacion/{identificador}")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> changeStatus(@PathVariable UUID identificador) {
        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorOrganizacionEncargado>();

        try {
            service.cambiarEstado(identificador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_ESTADO_EDITADO_FINAL);

        }catch (Exception exception) {
            log.error(exception.getMessage());
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_ESTADO_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @DeleteMapping("/administradororganizacion/{identificador}")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> eliminar(@RequestBody AdministradorOrganizacionEncargado administrador) {

        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorOrganizacionEncargado>();

        try {
            service.eliminar(administrador);
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_ELIMINADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerAdministradorOrganizacionEncargado.ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_ELIMINADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }


}