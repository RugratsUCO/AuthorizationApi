package com.authorizationapi.authorizationapi.service.jwt;

import com.authorizationapi.authorizationapi.auth.AuthResponse;
import com.authorizationapi.authorizationapi.auth.LoginRequest;
import com.authorizationapi.authorizationapi.auth.RegisterRequest;
import com.authorizationapi.authorizationapi.domain.persona.Rol;
import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import com.authorizationapi.authorizationapi.jwt.JwtService;
import com.authorizationapi.authorizationapi.repository.persona.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final JwtService service;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse login(LoginRequest request) {
        UserDetails usuario = repository.findByUsername(request.getUsername()).orElseThrow();
        String token = service.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .username(request.getCorreo())
                .password(passwordEncoder.encode(request.getContrasena()))
                .rol(Rol.ADMINISTRADOR_ORGANIZACION)
                .primerNombre(request.getPrimerNombre())
                .segundoNombre(request.getSegundoNombre())
                .primerApellido(request.getPrimerApellido())
                .segundoApellido(request.getSegundoApellido())
                .tipoIdentificacion(request.getTipoIdentificacion())
                .numeroIdentificacion(request.getNumeroIdentificacion())
                .paisTelefono(request.getPaisTelefono())
                .numeroTelefono(request.getNumeroTelefono())
                .build();
        repository.save(usuario);

        return AuthResponse.builder()
                .token(service.getToken(usuario))
                .build();
    }
    public Usuario consultar(Usuario usuario) {
        return repository.findById(usuario.getIdentificador()).orElse(null);
    }

}
