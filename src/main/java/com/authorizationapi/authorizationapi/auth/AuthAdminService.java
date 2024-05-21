package com.authorizationapi.authorizationapi.auth;

import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.domain.persona.Rol;
import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import com.authorizationapi.authorizationapi.messages.RabbitMQPublisher;
import com.authorizationapi.authorizationapi.repository.organizacion.OrganizacionRepository;
import com.authorizationapi.authorizationapi.repository.persona.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthAdminService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OrganizacionRepository organizacionRepository;
    @Autowired
    RabbitMQPublisher publisherEstructura;


    public boolean tienePermisosEnEstructura(Usuario usuario, Estructura estructura, UUID identificadorEstructura) {
        if (usuario.getRol() == Rol.ADMINISTRADOR_ESTRUCTURA) {
            return estructuraEsAdministradaPorUsuario(estructura,identificadorEstructura);
        }
        return false;
    }
    public boolean tienePermisosEnOrganizacion(Usuario usuario, Organizacion organizacion, UUID identificadorOrganizacion) {
        if (usuario.getRol() == Rol.ADMINISTRADOR_ORGANIZACION) {
            return organizacionEsAdministradaPorUsuario(organizacion,identificadorOrganizacion);
        }
        return false;
    }
    private boolean estructuraEsAdministradaPorUsuario(Estructura estructura, UUID identificadorEstructura) {
        return publisherEstructura.consultarPorId(estructura).equals(identificadorEstructura);
    }
    private boolean organizacionEsAdministradaPorUsuario(Organizacion organizacion, UUID identificadorOrganizacion) {
        return organizacionRepository.findById(organizacion.getIdentificador()).orElse(Organizacion.create()).getIdentificador().equals(identificadorOrganizacion);
    }
}

