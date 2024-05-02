package com.authorizationapi.authorizationapi.service.organizacion;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.repository.organizacion.AdministradorOrganizacionEncargadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class AdministradorOrganizacionEncargadoService {
    @Autowired
    private AdministradorOrganizacionEncargadoRepository repository;
    public void concederPermisos(AdministradorOrganizacionEncargado administrador) {
        UUID identificador;
        Optional<AdministradorOrganizacionEncargado> administradorOptional;
        do {
            identificador = UtilUUID.generateNewUUID();
            administradorOptional = repository.findById(identificador);
        } while (administradorOptional.isPresent());
        administrador.setIdentificador(identificador);

        repository.save(administrador);
    }

    public void cambiarEstado(UUID identificador) {
        Optional<AdministradorOrganizacionEncargado> administradorOptional = repository.findById(identificador);

        if (administradorOptional.isPresent()) {
            AdministradorOrganizacionEncargado administradorExistente = administradorOptional.get();
            administradorExistente.setActivo(UtilBoolean.getOpposite(administradorExistente.isActivo()));

            repository.save(administradorExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceAdministradorOrganizacionEncargado.ADMINISTRADOR_NO_ENCONTRADO_IDENTIFICADOR + identificador);
        }
    }

    public List<AdministradorOrganizacionEncargado> consultar() {
        return repository.findAll();
    }

    public void eliminar(AdministradorOrganizacionEncargado administrador) {
        Optional<AdministradorOrganizacionEncargado> administradorOptional = repository.findById(administrador.getIdentificador());

        if (administradorOptional.isPresent()) {
            repository.delete(administrador);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceAdministradorOrganizacionEncargado.ADMINISTRADOR_NO_ENCONTRADO);
        }
    }

}