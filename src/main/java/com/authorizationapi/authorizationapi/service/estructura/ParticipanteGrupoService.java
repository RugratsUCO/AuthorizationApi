package com.authorizationapi.authorizationapi.service.estructura;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.estructura.ParticipanteGrupo;
import com.authorizationapi.authorizationapi.repository.ParticipanteGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class ParticipanteGrupoService {

    @Autowired
    private ParticipanteGrupoRepository repository;

    public void asignarGrupo(ParticipanteGrupo participante) {
        UUID identificador;
        Optional<ParticipanteGrupo> participanteOptional;
        do {
            identificador = UtilUUID.generateNewUUID();
            participanteOptional = repository.findById(identificador);
        } while (participanteOptional.isPresent());
        participante.setIdentificador(identificador);

        repository.save(participante);
    }

    public List<ParticipanteGrupo> consultar() {
        return repository.findAll();
    }


    public void eliminar(ParticipanteGrupo participante) {
        Optional<ParticipanteGrupo> participanteOptional = repository.findById(participante.getIdentificador());

        if (participanteOptional.isPresent()) {
            repository.delete(participante);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceParticipanteGrupo.PARTICIPANTE_GRUPO_NO_ENCONTRADO);
        }
    }

}