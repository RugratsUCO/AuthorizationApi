package com.authorizationapi.authorizationapi.dto.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EstructuraDTO {
    HttpStatus estado;
    Estructura estructura;
    public EstructuraDTO(){
        estado = HttpStatus.FORBIDDEN;
    }
}
