package com.authorizationapi.authorizationapi.auth;

import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.domain.persona.Rol;
import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import com.authorizationapi.authorizationapi.repository.organizacion.AdministradorOrganizacionEncargadoRepository;
import com.authorizationapi.authorizationapi.repository.organizacion.OrganizacionRepository;
import com.authorizationapi.authorizationapi.repository.persona.PersonaRepository;
import com.authorizationapi.authorizationapi.repository.persona.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthAdminService {
    private final AdministradorOrganizacionEncargadoRepository adminOrganizacionRepository;
    private final OrganizacionRepository organizacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public AuthAdminService(AdministradorOrganizacionEncargadoRepository adminOrganizacionRepository,OrganizacionRepository organizacionRepository,UsuarioRepository usuarioRepository,PersonaRepository personaRepository) {
        this.adminOrganizacionRepository = adminOrganizacionRepository;
        this.organizacionRepository = organizacionRepository;
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean tienePermisosEnEstructura(AdministradorOrganizacionEncargado administrador,Estructura estructura ) {
        return administrador.getPersona().getUsuario().getRol() == Rol.ADMINISTRADOR_ORGANIZACION && administrador.getOrganizacion().getIdentificador().equals(estructura.getOrganizacion().getIdentificador());
    }
    public boolean tienePermisosEnOrganizacion(AdministradorOrganizacionEncargado administrador) {
        if (administrador.getPersona().getUsuario().getRol() == Rol.ADMINISTRADOR_ORGANIZACION) {
            return organizacionRepository.findById(administrador.getOrganizacion().getIdentificador()).orElse(Organizacion.create()).getIdentificador().equals(administrador.getOrganizacion().getIdentificador());
        }
        return false;
    }
    public AdministradorOrganizacionEncargado traerAdministradorOrganizacionDeCorreo(String correo){
        Usuario usuario = usuarioRepository.findByUsername(correo).orElseThrow();
        Persona persona = personaRepository.findByUsuario(usuario);
        return adminOrganizacionRepository.findByPersona(persona);

    }

}

