package com.authorizationapi.authorizationapi.service.estructura;

import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import com.authorizationapi.authorizationapi.repository.AdministradorEstructuraEncargadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class AdministradorEstructuraEncargadoService {

    @Autowired
    private AdministradorEstructuraEncargadoRepository repository;
    public void concederPermisos(AdministradorEstructuraEncargado administrador) {
        repository.save(administrador);
    }

    public void cambiarEstado(UUID identificador, AdministradorEstructuraEncargado nuevoAdministrador) {
        Optional<AdministradorEstructuraEncargado> administradorOptional = repository.findById(identificador);

        if (administradorOptional.isPresent()) {
            AdministradorEstructuraEncargado administradorExistente = administradorOptional.get();
            administradorExistente.setEstructura(nuevoAdministrador.getEstructura()).
                    setPersona(nuevoAdministrador.getPersona()).setActivo(nuevoAdministrador.isActivo());


            repository.save(administradorExistente);
        } else {

            throw new RuntimeException("Administrador no encontrado con el identificador: " + identificador);
        }

    }

    public List<AdministradorEstructuraEncargado> consultar() {
        return repository.findAll();
    }

    public void eliminar(AdministradorEstructuraEncargado administradorEstructuraEncargado) {
        repository.delete(administradorEstructuraEncargado);
    }

  
}
