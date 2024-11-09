package com.authorizationapi.authorizationapi.controller.publicacion;


import com.authorizationapi.authorizationapi.auth.AuthAdminService;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilDate;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.estructura.Grupo;
import com.authorizationapi.authorizationapi.domain.publicacion.Publicacion;
import com.authorizationapi.authorizationapi.messages.publicacion.PublicacionPublisher;
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
public final class PublicacionController {

    private final PublicacionPublisher publisher;
    private final AuthAdminService authService;
    private final Logger log = LoggerFactory.getLogger(PublicacionController.class);

    @Autowired
    public PublicacionController(AuthAdminService authService, PublicacionPublisher publisher) {
        this.authService = authService;
        this.publisher = publisher;
    }
    @PostMapping("/publicacion/{correo}")
    public ResponseEntity<Response<Publicacion>> publicar(@RequestBody Publicacion publicacion, @PathVariable String correo) {

        var estadoCreacion = HttpStatus.OK;
        Response<Publicacion> response = new Response<>();

        try {
            publicacion.setAutor(authService.traerParticipanteGrupoDeCorreo(correo));
            publicacion.setFechaPublicacion(UtilDate.getDefaultValueDate());
            estadoCreacion = publisher.publicar(publicacion);
            if(estadoCreacion == HttpStatus.OK){
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_REGISTRADA_FINAL);
            }

        } catch (Exception exception) {
            estadoCreacion = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_REGISTRADA_FINAL);
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>(response, estadoCreacion);
    }

    @GetMapping("/publicacionGrupo/{correo}")
    public ResponseEntity<Response<Publicacion>> consultarPorGrupo(@RequestBody Grupo grupo, @PathVariable String correo) {

        var statusCode = HttpStatus.OK;
        Response<Publicacion> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Publicacion> publicacionesConsultadas = new ArrayList<>();

            if(authService.puedePublicar(correo, grupo)){
                publicacionesConsultadas = publisher.listarPorGrupo(grupo);
            }

            if (publicacionesConsultadas != null && !publicacionesConsultadas.isEmpty()) {
                messages.add(UtilMessagesController.ControllerPublicacion.PUBLICACIONES_CONSULTADAS_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerPublicacion.PUBLICACIONES_NO_CONSULTADAS_FINAL);
            }

            response = new Response<>(publicacionesConsultadas,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_CONSULTADA_INTERNO_FINAL);
            log.error(exception.getMessage());
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/publicacion")
    public ResponseEntity<Response<Publicacion>> editar(@RequestBody Publicacion publicacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Publicacion>();

        try {
            HttpStatus estadoTransaccion = publisher.editar(publicacion);
            if(estadoTransaccion == HttpStatus.OK){
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_ACTUALIZADA_FINAL);
            }else{
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_ACTUALIZADA_FINAL);
            }
        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_ACTUALIZADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @PatchMapping("/publicacion/{identificador}")
    public ResponseEntity<Response<Publicacion>> cambiarEstado(@PathVariable String identificador) {
        UUID identificadorUUID = UtilUUID.generateUUIDFromString(identificador);
        var statusCode = HttpStatus.OK;
        var response = new Response<Publicacion>();

        try {
            HttpStatus estadoTransaccion = publisher.cambiarEstado(identificadorUUID);
            if (estadoTransaccion == HttpStatus.OK){
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_ACTUALIZADA_FINAL);
            }else{
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_ACTUALIZADA_FINAL);
            }
        }catch (Exception exception) {
            log.error(exception.getMessage());
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_ACTUALIZADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @DeleteMapping("/publicacion")
    public ResponseEntity<Response<Publicacion>> eliminar(@RequestBody Publicacion publicacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Publicacion>();

        try {
            HttpStatus estadoTransaccion = publisher.eliminar(publicacion);
            if(estadoTransaccion == HttpStatus.OK){
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_ELIMINADA_FINAL);
            }else{
                response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_ELIMINADA_FINAL);
            }
        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_ELIMINADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
