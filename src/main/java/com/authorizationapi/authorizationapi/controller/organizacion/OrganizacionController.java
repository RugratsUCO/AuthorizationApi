package com.authorizationapi.authorizationapi.controller.organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilMessages;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.service.organizacion.OrganizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authorization/api/v1")
public final class OrganizacionController {

    @Autowired
    private final OrganizacionService service = new OrganizacionService();
    @PostMapping("/organizacion")
    public ResponseEntity<Response<Organizacion>> crearNueva(@RequestBody Organizacion organizacion) {


        var statusCode = HttpStatus.OK;
        Response<Organizacion> response = new Response<>();

        try {
            service.crearNueva(organizacion);
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_CREADA_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_NO_CREADA_FINAL);

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/organizacion")
    public ResponseEntity<Response<Organizacion>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<Organizacion> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Organizacion> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add(UtilMessages.ControllerOrganizacion.ORGANIZACIONES_CONSULDATAS_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessages.ControllerOrganizacion.ORGANIZACIONES_NO_CONSULTADAS_FINAL);
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACIONES_NO_CONSULTADAS_INTERNO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionNombre/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarNombre(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarNombre(identificador, organizacion);
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionEstado/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarEstado(identificador, organizacion);
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_ESTADO_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_ESTADO_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/organizacion/{identificador}")
    public ResponseEntity<Response<Organizacion>> eliminar(@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.eliminar(organizacion);
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_ELIMINADA);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessages.ControllerOrganizacion.ORGANIZACION_NO_ELIMINADA);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
