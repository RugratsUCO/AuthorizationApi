package com.authorizationapi.authorizationapi.service.estructura;


import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import com.authorizationapi.authorizationapi.repository.ParticipanteRepository;
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
        repository.save(participante);
    }


    public void cambiarEstado(UUID identificador, Participante nuevoParticipante) {
        Optional<Participante> participanteOptional = repository.findById(identificador);

        if (participanteOptional.isPresent()) {
            Participante participanteExistente = participanteOptional.get();
            participanteExistente.setActivo(nuevoParticipante.getActivo());

            repository.save(participanteExistente);
        } else {

            throw new RuntimeException("Participante no encontrado con el identificador: " + identificador);
        }
    }


    public List<Participante> consultar() {

        return repository.findAll();
    }


    public void eliminar(Participante participante) {
        repository.delete(participante);
    }

}
