package com.authorizationapi.authorizationapi.service.persona;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.repository.persona.PersonaRepository;
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

            throw AuthorizationServiceException.create(UtilMessagesService.ServicePersona.PERSONA_NO_ENCONTRADA_IDENTIFICADOR + identificador);
        }
    }

    public Persona consultar(Persona persona) {
        return repository.findById(persona.getIdentificador()).orElse(null);
    }
    public List<Persona> consultarTodas() {
        return repository.findAll();
    }

    public void cambiarEstado(UUID identificador) {
        Optional<Persona> personaOptional = repository.findById(identificador);

        if (personaOptional.isPresent()){
            Persona personaExistente = personaOptional.get();
            personaExistente.setActivo(UtilBoolean.getOpposite(personaExistente.getActivo()));

            repository.save(personaExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServicePersona.PERSONA_NO_ENCONTRADA_IDENTIFICADOR + identificador);
        }
    }

    public void eliminar(Persona persona) {
        Optional<Persona> personaOptional = repository.findById(persona.getIdentificador());

        if (personaOptional.isPresent()) {
            repository.delete(persona);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServicePersona.PERSONA_NO_ENCONTRADA);
        }
    }
}