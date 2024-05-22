package com.authorizationapi.authorizationapi.dto;

import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class EstructuraDTO {
    HttpStatus estado;
    Estructura estructura;
}
