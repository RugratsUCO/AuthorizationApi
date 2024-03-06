package com.authorizationapi.authorizationapi.service.estructura;


import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.repository.EstructuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class EstructuraService {

    @Autowired
    private EstructuraRepository repository;
    public void crearNueva(Estructura estructura) {
        repository.save(estructura);
    }

    public void cambiarNombre(UUID identificador, Estructura nuevaEstructuraNombre) {
        Optional<Estructura> estructuraOptional = repository.findById(identificador);

        if (estructuraOptional.isPresent()) {
            Estructura estructuraExistente = estructuraOptional.get();
            estructuraExistente.setNombre(nuevaEstructuraNombre.getNombre());

            repository.save(estructuraExistente);
        } else {

            throw new RuntimeException("Estructura no encontrada con el identificador: " + identificador);
        }
    }

    public void cambiarEstado(UUID identificador, Estructura nuevaEstructuraNombre) {
        Optional<Estructura> estructuraOptional = repository.findById(identificador);

        if (estructuraOptional.isPresent()) {
            Estructura estructuraExistente = estructuraOptional.get();
            estructuraExistente.setActivo(nuevaEstructuraNombre.getActivo());

            repository.save(estructuraExistente);
        } else {

            throw new RuntimeException("Estructura no encontrada con el identificador: " + identificador);
        }
    }

    public List<Estructura> consultar() {
        return repository.findAll();
    }

    public void eliminar(Estructura estructura) {
        repository.delete(estructura);
    }

 
}
