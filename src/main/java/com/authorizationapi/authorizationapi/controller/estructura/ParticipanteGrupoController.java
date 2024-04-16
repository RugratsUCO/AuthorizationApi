package com.authorizationapi.authorizationapi.controller.estructura;

import java.util.ArrayList;
import java.util.List;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.estructura.ParticipanteGrupo;
import com.authorizationapi.authorizationapi.service.estructura.ParticipanteGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authorization/api/v1")
public final class ParticipanteGrupoController {

    @Autowired
    private final ParticipanteGrupoService service = new ParticipanteGrupoService();
    @PostMapping("/participantegrupo")
    public ResponseEntity<Response<ParticipanteGrupo>> asignarGrupo(@RequestBody ParticipanteGrupo participante) {


        var statusCode = HttpStatus.OK;
        Response<ParticipanteGrupo> response = new Response<>();

        try {
            service.asignarGrupo(participante);
            response.getMessages().add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTE_GRUPO_CREADO_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTE_GRUPO_NO_CREADO_FINAL);

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/participantegrupo")
    public ResponseEntity<Response<ParticipanteGrupo>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<ParticipanteGrupo> response;

        try {
            List<String> messages = new ArrayList<>();
            List<ParticipanteGrupo> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTES_GRUPO_CONSULTADOS_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTES_GRUPO_NO_CONSULTADOS_FINAL);
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTES_GRUPO_NO_CONSULTADOS_INTERNO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/participantegrupo/{identificador}")
    public ResponseEntity<Response<ParticipanteGrupo>> eliminar(@RequestBody ParticipanteGrupo participante) {

        var statusCode = HttpStatus.OK;
        var response = new Response<ParticipanteGrupo>();

        try {
            service.eliminar(participante);
            response.getMessages().add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTE_GRUPO_ELIMINADO);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerParticipanteGrupo.PARTICIPANTE_GRUPO_NO_ELIMINADO);
        }

        return new ResponseEntity<>(response,statusCode);
    }

}