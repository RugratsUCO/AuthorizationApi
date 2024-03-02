package com.authorizationapi.authorizationapi.service.persona;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public final class PersonaService {
    List<Persona> personas = new ArrayList<>();

    public void registrar(Persona persona) {
        List<Persona> result;

        do {
            persona.setIdentificador(UtilUUID.generateNewUUID());
            result = this.consultar(persona);
        }while(!result.isEmpty());

        personas.add(persona);
    }

    public void editar(Persona persona) {
        for (Persona personaAEditar : personas){
            if(personaAEditar.getIdentificador().equals(persona.getIdentificador())){
                eliminar(personaAEditar.getIdentificador());
                personas.add(persona);
            }
        }
    }

    public List<Persona> consultar(Persona persona) {
        List<Persona> personasConsultadas = new ArrayList<>();
        for (Persona personaAConsultar: personas){
            if(personaAConsultar.getIdentificador().equals(persona.getIdentificador())){
                personasConsultadas.add(personaAConsultar);
            }
        }
        return personasConsultadas;
    }

    public List<Persona> consultarTodas(){
        return personas;
    }
    public void eliminar(UUID id) {
        personas.removeIf(personaAEliminar -> personaAEliminar.getIdentificador().equals(id));
    }
}
