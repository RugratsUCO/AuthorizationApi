package com.authorizationapi.authorizationapi.service.business;


import com.authorizationapi.authorizationapi.domain.PersonaDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public final class PersonaService {


    public void registrar(PersonaDomain persona) {
    }

    public void editar(PersonaDomain persona) {

    }


    public void cambiarEstado(PersonaDomain persona) {

    }


    public List<PersonaDomain> consultar(PersonaDomain persona) {
        return List.of(persona);
    }


    public void eliminar(UUID persona) {

    }
}
