package com.authorizationapi.authorizationapi.service.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.estructura.AdministradorEstructuraEncargado;
import com.authorizationapi.authorizationapi.repository.estructura.AdministradorEstructuraEncargadoRepository;
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
        UUID identificador;
        Optional<AdministradorEstructuraEncargado> administradorOptional;
        do {
            identificador = UtilUUID.generateNewUUID();
            administradorOptional = repository.findById(identificador);
        } while (administradorOptional.isPresent());
        administrador.setIdentificador(identificador);

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

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_NO_ENCONTRADO_IDENTIFICADOR + identificador);
        }

    }

    public List<AdministradorEstructuraEncargado> consultar() {
        return repository.findAll();
    }

    public void eliminar(AdministradorEstructuraEncargado administrador) {
        Optional<AdministradorEstructuraEncargado> administradorOptional = repository.findById(administrador.getIdentificador());

        if (administradorOptional.isPresent()) {
            repository.delete(administrador);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceAdministradorEstructuraEncargado.ADMINISTRADOR_ESTRUCTURA_NO_ENCONTRADO);
        }
    }


}
