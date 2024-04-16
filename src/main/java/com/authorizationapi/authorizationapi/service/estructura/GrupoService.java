package com.authorizationapi.authorizationapi.service.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.estructura.Grupo;
import com.authorizationapi.authorizationapi.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class GrupoService {

    @Autowired
    private GrupoRepository repository;
    public void crear(Grupo grupo) {
        UUID identificador;
        Optional<Grupo> grupoOptional;
        do {
            identificador = UtilUUID.generateNewUUID();
            grupoOptional = repository.findById(identificador);
        } while (grupoOptional.isPresent());
        grupo.setIdentificador(identificador);

        repository.save(grupo);
    }


    public void editar(UUID identificador, Grupo nuevoGrupo) {
        Optional<Grupo> grupoOptional = repository.findById(identificador);

        if (grupoOptional.isPresent()) {
            Grupo grupoExistente = grupoOptional.get();
            grupoExistente.setNombre(nuevoGrupo.getNombre()).setEstructura(nuevoGrupo.getEstructura())
                    .setActivo(nuevoGrupo.getActivo());

            repository.save(grupoExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceGrupo.GRUPO_NO_ENCONTRADO + identificador);
        }
    }


    public void cambiarEstado(UUID identificador, Grupo nuevoGrupo) {
        Optional<Grupo> grupoOptional = repository.findById(identificador);

        if (grupoOptional.isPresent()) {
            Grupo grupoExistente = grupoOptional.get();
            grupoExistente.setActivo(nuevoGrupo.getActivo());

            repository.save(grupoExistente);
        } else {

            throw new RuntimeException(UtilMessagesService.ServiceGrupo.GRUPO_NO_ENCONTRADO_IDENTIFICADOR + identificador);
        }
    }

    public List<Grupo> buscar() {
        return repository.findAll();
    }

    public void eliminar(Grupo grupo) {
        Optional<Grupo> grupoOptional = repository.findById(grupo.getIdentificador());

        if (grupoOptional.isPresent()) {
            repository.delete(grupo);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceGrupo.GRUPO_NO_ENCONTRADO);
        }
    }

}