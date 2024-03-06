package com.authorizationapi.authorizationapi.service.estructura;

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

            throw new RuntimeException("Grupo no encontrado con el identificador: " + identificador);
        }
    }


    public void cambiarEstado(UUID identificador, Grupo nuevoGrupo) {
        Optional<Grupo> grupoOptional = repository.findById(identificador);

        if (grupoOptional.isPresent()) {
            Grupo grupoExistente = grupoOptional.get();
            grupoExistente.setActivo(nuevoGrupo.getActivo());

            repository.save(grupoExistente);
        } else {

            throw new RuntimeException("Grupo no encontrado con el identificador: " + identificador);
        }
    }

    public List<Grupo> buscar() {
        return repository.findAll();
    }

    public void eliminar(Grupo grupo) {
        repository.delete(grupo);
    }

}
