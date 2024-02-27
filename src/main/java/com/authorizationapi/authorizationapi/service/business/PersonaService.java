package com.authorizationapi.authorizationapi.service.business;


import com.authorizationapi.authorizationapi.domain.PersonaDomain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public final class PersonaService {

    private List<PersonaDomain> personas = new ArrayList<PersonaDomain>();
    public void registrar(PersonaDomain persona) {
        if(persona.getCorreo().equals("")){

        }
        persona.setIdentificador(UUID.randomUUID());
        personas.add(persona);
    }

    public void editar(PersonaDomain persona) {

    }


    public void cambiarEstado(PersonaDomain persona) {

    }


    public List<PersonaDomain> consultar(PersonaDomain persona) {
        List<PersonaDomain> personasConsultadas = new ArrayList<PersonaDomain>();

        for (PersonaDomain personas : personas){
            if(persona.getPrimerNombre().equals(personas.getPrimerNombre())){
                personasConsultadas.add(personas);
            }
        }
        return personasConsultadas;
    }


    public void eliminar(UUID persona) {

    }
}
