package com.authorizationapi.authorizationapi.auth;

import com.authorizationapi.authorizationapi.controller.organizacion.AdministradorOrganizacionEncargadoController;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import com.authorizationapi.authorizationapi.service.jwt.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final Logger log = LoggerFactory.getLogger(AdministradorOrganizacionEncargadoController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        AuthResponse usuario;
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            usuario = authService.login(request);
        }catch (Exception e){
            usuario = new AuthResponse();
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        AuthResponse usuario;
        try{
            usuario = authService.register(request);
        }catch (Exception e){
            usuario = new AuthResponse();
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/personaById")
    public ResponseEntity<Response<Usuario>> listById(@RequestBody Usuario persona) {
        var statusCode = HttpStatus.OK;
        Response<Usuario> response = new Response<>();
        try {
            List<String> messages = new ArrayList<>();
            Usuario personaConsultada = authService.consultar(persona);
            List<Usuario> list = List.of(personaConsultada);
            response = new Response<>(list,messages);
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
