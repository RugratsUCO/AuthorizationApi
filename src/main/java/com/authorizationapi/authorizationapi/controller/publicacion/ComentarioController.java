package com.authorizationapi.authorizationapi.controller.publicacion;

import com.authorizationapi.authorizationapi.auth.AuthAdminService;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilDate;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.publicacion.Comentario;
import com.authorizationapi.authorizationapi.domain.publicacion.Publicacion;
import com.authorizationapi.authorizationapi.messages.publicacion.ComentarioPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("authorization/api/v1")
@CrossOrigin(origins = {"http://localhost:4200"})
public final class ComentarioController {

    private final ComentarioPublisher publisher;
    private final AuthAdminService authService;
    private final Logger log = LoggerFactory.getLogger(ComentarioController.class);

    @Autowired
    public ComentarioController(AuthAdminService authService, ComentarioPublisher publisher) {
        this.authService = authService;
        this.publisher = publisher;
    }
    @PostMapping("/comentario/{correo}")
    public ResponseEntity<Response<Publicacion>> comentar(@RequestBody Comentario comentario, @PathVariable String correo) {

        var estadoCreacion = HttpStatus.OK;
        Response<Publicacion> response = new Response<>();

        try {
            comentario.setAutor(authService.traerParticipanteGrupoDeCorreo(correo));
            comentario.setFechaComentario(UtilDate.getDefaultValueDate());
            estadoCreacion = publisher.comentar(comentario);
            if(estadoCreacion == HttpStatus.OK){
                response.getMessages().add(UtilMessagesController.ControllerComentario.COMENTARIO_REGISTRADO_FINAL);
            }

        } catch (Exception exception) {
            estadoCreacion = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerComentario.COMENTARIO_NO_REGISTRADO_FINAL);
            log.error(exception.getMessage());
        }
        return new ResponseEntity<>(response, estadoCreacion);
    }
    @GetMapping("/comentarioPublicacion")
    public ResponseEntity<Response<Comentario>> consultarPorGrupo(@RequestBody Publicacion publicacion) {

        var statusCode = HttpStatus.OK;
        Response<Comentario> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Comentario> comentariosConsultados = publisher.listarPorPublicacion(publicacion);

            if (comentariosConsultados != null && !comentariosConsultados.isEmpty()) {
                messages.add(UtilMessagesController.ControllerComentario.COMENTARIOS_CONSULTADOS_FINAL);
            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerComentario.COMENTARIOS_NO_CONSULTADOS_FINAL);
            }

            response = new Response<>(comentariosConsultados,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerPublicacion.PUBLICACION_NO_CONSULTADA_INTERNO_FINAL);
            log.error(exception.getMessage());
        }

        return new ResponseEntity<>(response,statusCode);
    }

}

