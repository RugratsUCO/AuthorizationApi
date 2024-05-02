package com.authorizationapi.authorizationapi.service.estructura;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import com.authorizationapi.authorizationapi.repository.estructura.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class ParticipanteService {

    @Autowired
    private ParticipanteRepository repository;
    public void registrar(Participante participante) {
        UUID identificador;
        Optional<Participante> participanteOptional;
        do {
            identificador = UtilUUID.generateNewUUID();
            participanteOptional = repository.findById(identificador);
        } while (participanteOptional.isPresent());
        participante.setIdentificador(identificador);

        repository.save(participante);
    }


    public void cambiarEstado(UUID identificador, Participante nuevoParticipante) {
        Optional<Participante> participanteOptional = repository.findById(identificador);

        if (participanteOptional.isPresent()) {
            Participante participanteExistente = participanteOptional.get();
            participanteExistente.setActivo(nuevoParticipante.getActivo());

            repository.save(participanteExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceParticipante.PARTICIPANTE_NO_ENCONTRADO);
        }
    }


    public List<Participante> consultar() {

        return repository.findAll();
    }


    public void eliminar(Participante participante) {
        Optional<Participante> participanteOptional = repository.findById(participante.getIdentificador());

        if (participanteOptional.isPresent()) {
            repository.delete(participante);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceParticipante.PARTICIPANTE_NO_ENCONTRADO_IDENTIFICADOR + participante.getIdentificador());
        }
    }

}