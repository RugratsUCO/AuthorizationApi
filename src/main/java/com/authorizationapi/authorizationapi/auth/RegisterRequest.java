package com.authorizationapi.authorizationapi.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String correo;
    String contrasena;
    String tipoIdentificacion;

    String numeroIdentificacion;

    String primerNombre;

    String segundoNombre;

    String primerApellido;

    String segundoApellido;
    String paisTelefono;

    String numeroTelefono;


}
