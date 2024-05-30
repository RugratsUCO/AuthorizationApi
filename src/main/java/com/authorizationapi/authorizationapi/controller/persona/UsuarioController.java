package com.authorizationapi.authorizationapi.controller.persona;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import com.authorizationapi.authorizationapi.service.persona.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("authorization/api/v1")
public class UsuarioController {
    private final UsuarioService service;

    private final Logger log = LoggerFactory.getLogger(PersonaController.class);
    public UsuarioController(UsuarioService service){
        this.service = service;
    }
    @GetMapping("/usuario/{correo}")
    public ResponseEntity<Response<Usuario>> consultar(@PathVariable String correo) {

        var statusCode = HttpStatus.OK;
        Response<Usuario> response = new Response<>();

        try {
            List<String> messages = new ArrayList<>();
            Usuario usuarioConsultado = service.consultarUsuario(correo);

            if (usuarioConsultado != null) {
                messages.add(UtilMessagesController.ControllerPersona.PERSONAS_CONSULTADAS_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_FINAL);
                log.error((UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_TECNICO));
            }

            assert usuarioConsultado != null;
            response = new Response<>(List.of(usuarioConsultado),messages);

        } catch (AuthorizationException exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerPersona.PERSONA_NO_CONSULTADA_INTERNO_FINAL);
            log.error(exception.getTechnicalMessage());
        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(exception.getMessage());
            log.error(UtilMessagesController.ControllerPersona.PERSONAS_NO_CONSULTADAS_TECNICO);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
