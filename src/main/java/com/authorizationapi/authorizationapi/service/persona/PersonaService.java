package com.authorizationapi.authorizationapi.service.persona;


import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class PersonaService {

    @Autowired
    private PersonaRepository repository;

    public void registrar(Persona persona) {
        repository.save(persona);
    }

    public void editar(UUID identificador, Persona nuevaPersona) {
        Optional<Persona> personaOptional = repository.findById(identificador);

        if (personaOptional.isPresent()) {
            Persona personaExistente = personaOptional.get();
            personaExistente.setCorreo(nuevaPersona.getCorreo()).setActivo(nuevaPersona.getActivo())
                    .setPrimerNombre(nuevaPersona.getPrimerNombre()).setSegundoNombre(nuevaPersona.getSegundoNombre())
                    .setPrimerApellido(nuevaPersona.getPrimerApellido()).setSegundoApellido(nuevaPersona.getSegundoApellido())
                    .setNumeroIdentificacion(nuevaPersona.getNumeroIdentificacion()).setTipoIdentificacion(nuevaPersona.getTipoIdentificacion())
                    .setNumeroTelefono(nuevaPersona.getNumeroTelefono()).setPaisTelefono(nuevaPersona.getPaisTelefono());

            repository.save(personaExistente);
        } else {

            throw new RuntimeException("Participante no encontrado con el identificador: " + identificador);
        }
    }

    public Persona consultar(Persona persona) {
        return repository.findById(persona.getIdentificador()).orElse(null);
    }
    public List<Persona> consultarTodas() {
        return repository.findAll();
    }

    public void eliminar(Persona persona) {
        repository.delete(persona);
    }
}
