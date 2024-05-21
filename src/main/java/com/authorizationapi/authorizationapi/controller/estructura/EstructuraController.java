package com.authorizationapi.authorizationapi.controller.estructura;

import com.authorizationapi.authorizationapi.controller.organizacion.AdministradorOrganizacionEncargadoController;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.messages.RabbitMQPublisher;
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
@CrossOrigin(origins = {"http://localhost:4200"})
public final class EstructuraController {

    @Autowired
    RabbitMQPublisher publisher;
    private final Logger log = LoggerFactory.getLogger(AdministradorOrganizacionEncargadoController.class);

    @PostMapping("/estructura")
    public ResponseEntity<Response<Estructura>> crearNueva(@RequestBody List<Estructura> estructuras) {

        var statusCode = HttpStatus.OK;
        Response<Estructura> response = new Response<>();

        try {
            publisher.crearNueva(estructuras);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_CREADA_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NO_CREADA_FINAL);
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/estructura")
    public ResponseEntity<Response<Estructura>> consultarPorID(@RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        Response<Estructura> response;

        try {
            List<String> messages = new ArrayList<>();
            Estructura estructuraConsultada = publisher.consultarPorId(estructura);

            if (estructuraConsultada != null) {
                messages.add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_CONSULTADA_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_NO_CONSULTADAS_FINAL);
            }

            response = new Response<>(List.of(estructuraConsultada),messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_NO_CONSULTADAS_INTERNO_FINAL);
            log.error(exception.getMessage());
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @GetMapping("/estructuraTodas")
    public ResponseEntity<Response<Estructura>> consultarTodas() {

        var statusCode = HttpStatus.OK;
        Response<Estructura> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Estructura> estructurasConsultadas = publisher.consultarTodas();

            if (estructurasConsultadas != null || estructurasConsultadas.isEmpty()) {
                messages.add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_CONSULTADA_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_NO_CONSULTADAS_FINAL);
            }

            response = new Response<>(estructurasConsultadas,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_NO_CONSULTADAS_INTERNO_FINAL);
            log.error(exception.getMessage());
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/estructura")
    public ResponseEntity<Response<Estructura>> cambiarNombre(@RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            publisher.cambiarNombre(estructura);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @PatchMapping("/estructura/{identificador}")
    public ResponseEntity<Response<Estructura>> cambiarEstado(@PathVariable String identificador) {
        UUID identificadorUUID = UtilUUID.generateUUIDFromString(identificador);
        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            publisher.cambiarEstado(identificadorUUID);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_ESTADO_EDITADO_FINAL);

        }catch (Exception exception) {
            log.error(exception.getMessage());
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_ESTADO_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @DeleteMapping("/estructura")
    public ResponseEntity<Response<Estructura>> eliminar(@RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            publisher.eliminar(estructura);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_ELIMINADA_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NO_ELIMINADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}