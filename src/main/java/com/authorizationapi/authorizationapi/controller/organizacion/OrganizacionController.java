package com.authorizationapi.authorizationapi.controller.organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.auth.AuthAdminService;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.service.organizacion.OrganizacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authorization/api/v1")
public final class OrganizacionController {

    private final OrganizacionService service;
    private final AuthAdminService authService;
    private final Logger log = LoggerFactory.getLogger(OrganizacionController.class);
    public OrganizacionController(OrganizacionService service, AuthAdminService authService){
        this.authService = authService;
        this.service = service;
    }

    @PostMapping("/organizacion")
    public ResponseEntity<Response<Organizacion>> crearNueva(@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        Response<Organizacion> response = new Response<>();

        try {
            service.crearNueva(organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_CREADA_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NO_CREADA_FINAL);
            log.error((UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NO_REGISTRADA_TECNICO));
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/organizacion/{correo}")
    public ResponseEntity<Response<Organizacion>> consultar(@PathVariable String correo) {
        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            List<String> messages = new ArrayList<>();
            if(authService.esAdministradorOrganizacion(correo)){
               Organizacion organizacion = service.consultarPorId(authService.traerOrganizacionDeAdministrador(correo));
                if (organizacion != null) {
                    messages.add(UtilMessagesController.ControllerOrganizacion.ORGANIZACIONES_CONSULDATAS_FINAL);
                    response = new Response<>(List.of(organizacion), messages);
                }
            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerOrganizacion.ORGANIZACIONES_NO_CONSULTADAS_FINAL);
            }

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACIONES_NO_CONSULTADAS_INTERNO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionNombre/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarNombre(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarNombre(identificador, organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionEstado/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarNombre(identificador, organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @PatchMapping("/organizacion/{identificador}")
    public ResponseEntity<Response<Organizacion>> changeStatus(@PathVariable UUID identificador) {
        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarEstado(identificador);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_ESTADO_ACTUALIZADO_FINAL);

        }catch (Exception exception) {
            log.error(exception.getMessage());
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_ESTADO_NO_ACTUALIZADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @DeleteMapping("/organizacion/{identificador}")
    public ResponseEntity<Response<Organizacion>> eliminar(@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.eliminar(organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_ELIMINADA_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NO_ELIMINADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}