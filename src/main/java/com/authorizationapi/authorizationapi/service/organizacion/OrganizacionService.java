package com.authorizationapi.authorizationapi.service.organizacion;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.exception.AuthorizationServiceException;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesService;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.repository.organizacion.OrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class OrganizacionService {

    @Autowired
    private OrganizacionRepository repository;
    public void crearNueva(Organizacion organizacion) {
        repository.save(organizacion);
    }


    public void cambiarNombre(UUID identificador, Organizacion nuevaOrganizacion) {
        Optional<Organizacion> organizacionOptional = repository.findById(identificador);

        if (organizacionOptional.isPresent()) {
            Organizacion organizacionExistente = organizacionOptional.get();
            organizacionExistente.setNombre(nuevaOrganizacion.getNombre());

            repository.save(organizacionExistente);
        } else {

            throw AuthorizationServiceException.create(UtilMessagesService.ServiceOrganizacion.ORGANIZATION_NO_ENCONTRADA_IDENTIFICADOR + identificador);
        }
    }


    public void cambiarEstado(UUID identificador) {
        Optional<Organizacion> organizacionOptional = repository.findById(identificador);

        if (organizacionOptional.isPresent()) {
            Organizacion organizacionExistente = organizacionOptional.get();
            organizacionExistente.setActivo(UtilBoolean.getOpposite(organizacionExistente.getActivo()));

            repository.save(organizacionExistente);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceOrganizacion.ORGANIZATION_NO_ENCONTRADA_IDENTIFICADOR + identificador);
        }
    }


    public List<Organizacion> consultar() {
        return repository.findAll();
    }


    public void eliminar(Organizacion organizacion) {
        Optional<Organizacion> organizacionOptional = repository.findById(organizacion.getIdentificador());

        if (organizacionOptional.isPresent()) {
            repository.delete(organizacion);
        } else {
            throw AuthorizationServiceException.create(UtilMessagesService.ServiceOrganizacion.ORGANIZATION_NO_ENCONTRADA);
        }
    }
}