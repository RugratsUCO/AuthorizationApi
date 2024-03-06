package com.authorizationapi.authorizationapi.service.estructura;


import com.authorizationapi.authorizationapi.domain.estructura.ParticipanteGrupo;
import com.authorizationapi.authorizationapi.repository.ParticipanteGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public final class ParticipanteGrupoService {

    @Autowired
    private ParticipanteGrupoRepository repository;

    public void asignarGrupo(ParticipanteGrupo participante) {
        repository.save(participante);
    }

    public List<ParticipanteGrupo> consultar() {
        return repository.findAll();
    }


    public void eliminar(ParticipanteGrupo participante) {
        repository.delete(participante);
    }

}
