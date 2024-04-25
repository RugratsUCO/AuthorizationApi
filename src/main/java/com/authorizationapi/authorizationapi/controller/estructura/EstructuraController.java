package com.authorizationapi.authorizationapi.controller.estructura;

import com.authorizationapi.authorizationapi.config.QueueConfig;
import com.authorizationapi.authorizationapi.controller.organizacion.AdministradorOrganizacionEncargadoController;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.service.estructura.EstructuraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("authorization/api/v1")
public final class EstructuraController {
    @Autowired
    private final EstructuraService service = new EstructuraService();
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final Logger log = LoggerFactory.getLogger(AdministradorOrganizacionEncargadoController.class);

    @PostMapping("/estructura")
    public ResponseEntity<Response<Estructura>> crearNueva(@RequestBody Estructura estructura) {



        var statusCode = HttpStatus.OK;
        Response<Estructura> response = new Response<>();

        try {
            rabbitTemplate.convertAndSend(QueueConfig.EXCHANGE,"crear_estructura_key", estructura);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_CREADA_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NO_CREADA_FINAL);
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/estructura")
    public ResponseEntity<Response<Estructura>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<Estructura> response;

        try {
            List<String> messages = new ArrayList<>();
            //List<Estructura> list = (List<Estructura>) rabbitTemplate.receiveAndConvert("consultar_estructura_queue");
            List<Estructura> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_CONSULTADA_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_NO_CONSULTADAS_FINAL);
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURAS_NO_CONSULTADAS_INTERNO_FINAL);
            log.error(exception.getMessage());
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/estructura/{identificador}")
    public ResponseEntity<Response<Estructura>> editar(@PathVariable UUID identificador,@RequestBody Estructura estructura) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            rabbitTemplate.convertAndSend(QueueConfig.EXCHANGE,"cambiar_nombre_estructura_key", identificador);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @PatchMapping("/estructura/{identificador}")
    public ResponseEntity<Response<Estructura>> changeStatus(@PathVariable UUID identificador) {
        var statusCode = HttpStatus.OK;
        var response = new Response<Estructura>();

        try {
            rabbitTemplate.convertAndSend(QueueConfig.EXCHANGE,"editar_estructura_key", identificador);
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
            rabbitTemplate.convertAndSend(QueueConfig.EXCHANGE,"eliminar_estructura_key", estructura);
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_ELIMINADA_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerEstructura.ESTRUCTURA_NO_ELIMINADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}