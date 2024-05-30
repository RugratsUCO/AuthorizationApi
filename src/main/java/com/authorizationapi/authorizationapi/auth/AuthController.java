package com.authorizationapi.authorizationapi.auth;

import com.authorizationapi.authorizationapi.controller.organizacion.AdministradorOrganizacionEncargadoController;
import com.authorizationapi.authorizationapi.domain.persona.Rol;
import com.authorizationapi.authorizationapi.service.jwt.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorization")
public class AuthController {
    private final AuthService authService;
    private final Logger log = LoggerFactory.getLogger(AdministradorOrganizacionEncargadoController.class);
    private final AuthenticationManager authenticationManager;
    public AuthController(AuthService authService,AuthenticationManager authenticationManager){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

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

    @PostMapping(value = "registrarParticipante")
    public ResponseEntity<AuthResponse> registrarParticipante(@RequestBody RegisterRequest request){
        AuthResponse usuario;
        try{
            usuario = authService.register(request, Rol.PARTICIPANTE);
        }catch (Exception e){
            usuario = new AuthResponse();
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(usuario);
    }
    @PostMapping(value = "registrarAdministrador")
    public ResponseEntity<AuthResponse> registrarAdministradorOrganizacion(@RequestBody RegisterRequest request){
        AuthResponse usuario;
        try{
            usuario = authService.register(request, Rol.ADMINISTRADOR_ORGANIZACION);
        }catch (Exception e){
            usuario = new AuthResponse();
            log.error(e.getMessage());
        }
        return ResponseEntity.ok(usuario);
    }

}
