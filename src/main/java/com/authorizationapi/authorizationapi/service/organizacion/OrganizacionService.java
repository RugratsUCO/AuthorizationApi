package com.authorizationapi.authorizationapi.service.organizacion;


import com.authorizationapi.authorizationapi.crosscutting.utils.exception.ServiceException;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.repository.OrganizacionRepository;
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

            throw ServiceException.create("Organizacion no encontrada con el identificador: " + identificador);
        }
    }


    public void cambiarEstado(UUID identificador, Organizacion nuevaOrganizacion) {
        Optional<Organizacion> organizacionOptional = repository.findById(identificador);

        if (organizacionOptional.isPresent()) {
            Organizacion organizacionExistente = organizacionOptional.get();
            organizacionExistente.setActivo(nuevaOrganizacion.getActivo());

            repository.save(organizacionExistente);
        } else {

            throw ServiceException.create("Organizacion no encontrada con el identificador: " + identificador);
        }
    }


    public List<Organizacion> consultar() {
        return repository.findAll();
    }


    public void eliminar(Organizacion organizacion) {
        repository.delete(organizacion);
    }
}
