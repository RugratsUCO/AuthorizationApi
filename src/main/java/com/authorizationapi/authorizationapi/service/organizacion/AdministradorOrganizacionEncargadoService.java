package com.authorizationapi.authorizationapi.service.organizacion;

import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.repository.AdministradorOrganizacionEncargadoRepository;
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
        repository.save(administrador);
    }

    public void cambiarEstado(UUID identificador, AdministradorOrganizacionEncargado nuevoEstadoAdministrador) {
        Optional<AdministradorOrganizacionEncargado> administradorOptional = repository.findById(identificador);

        if (administradorOptional.isPresent()) {
            AdministradorOrganizacionEncargado administradorExistente = administradorOptional.get();
            administradorExistente.setActivo(nuevoEstadoAdministrador.isActivo());

            repository.save(administradorExistente);
        } else {

            throw new RuntimeException("Administrador organizacion no encontrado con el identificador: " + identificador);
        }
    }

    public List<AdministradorOrganizacionEncargado> consultar() {
        return repository.findAll();
    }

    public void eliminar(AdministradorOrganizacionEncargado administrador) {
        repository.delete(administrador);
    }

}
