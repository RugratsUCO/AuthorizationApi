package com.authorizationapi.authorizationapi.auth;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.domain.estructura.*;
import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import com.authorizationapi.authorizationapi.domain.persona.Rol;
import com.authorizationapi.authorizationapi.domain.persona.Usuario;
import com.authorizationapi.authorizationapi.repository.estructura.AdministradorEstructuraEncargadoRepository;
import com.authorizationapi.authorizationapi.repository.estructura.ParticipanteGrupoRepository;
import com.authorizationapi.authorizationapi.repository.estructura.ParticipanteRepository;
import com.authorizationapi.authorizationapi.repository.organizacion.AdministradorOrganizacionEncargadoRepository;
import com.authorizationapi.authorizationapi.repository.persona.PersonaRepository;
import com.authorizationapi.authorizationapi.repository.persona.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthAdminService {
    private final AdministradorOrganizacionEncargadoRepository adminOrganizacionRepository;
    private final AdministradorEstructuraEncargadoRepository adminEstructuraRepository;
    private final ParticipanteGrupoRepository participanteGrupoRepository;
    private final ParticipanteRepository participanteRepository;
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;


    public AuthAdminService(AdministradorOrganizacionEncargadoRepository adminOrganizacionRepository, AdministradorEstructuraEncargadoRepository adminEstructuraRepository, ParticipanteGrupoRepository participanteRepository, ParticipanteRepository participanteRepository1, UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.adminOrganizacionRepository = adminOrganizacionRepository;
        this.adminEstructuraRepository = adminEstructuraRepository;
        this.participanteGrupoRepository = participanteRepository;
        this.participanteRepository = participanteRepository1;
        this.personaRepository = personaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean tienePermisosEnEstructura(AdministradorEstructuraEncargado administrador, Estructura estructura ) {
        return !UtilObject.isNull(administrador) && administrador.getEstructura().getIdentificador().equals(estructura.getIdentificador()) && administrador.isActivo();
    }
    public boolean tienePermisosEnOrganizacion(AdministradorOrganizacionEncargado administrador, Organizacion organizacion) {
        return !UtilObject.isNull(administrador) && administrador.getOrganizacion().getIdentificador().equals(organizacion.getIdentificador()) && administrador.isActivo();
    }
    public boolean tienePermisos(String correo, Estructura estructura) {
        if(esAdministradorEstructura(correo)){
            AdministradorEstructuraEncargado administrador = traerAdministradorEstructuraDeCorreo(correo);
            return tienePermisosEnEstructura(administrador,estructura);
        }else if(esAdministradorOrganizacion(correo)){
            AdministradorOrganizacionEncargado administrador = traerAdministradorOrganizacionDeCorreo(correo);
            return tienePermisosEnOrganizacion(administrador,estructura.getOrganizacion());
        }
        return false;
    }
    public Persona traerPersonaDeCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByUsername(correo).orElseThrow();
        return personaRepository.findByUsuario(usuario);
    }
    public AdministradorOrganizacionEncargado traerAdministradorOrganizacionDeCorreo(String correo){
        Persona persona = traerPersonaDeCorreo(correo);
        return adminOrganizacionRepository.findByPersona(persona);

    }
    public AdministradorEstructuraEncargado traerAdministradorEstructuraDeCorreo(String correo){
        Persona persona = traerPersonaDeCorreo(correo);
        return adminEstructuraRepository.findByPersona(persona);

    }
    public Participante traerParticipanteDeCorreo(String correo){
        Persona persona = traerPersonaDeCorreo(correo);
        return participanteRepository.findByPersona(persona);

    }
    public ParticipanteGrupo traerParticipanteGrupoDeCorreo(String correo){
        Participante participante = traerParticipanteDeCorreo(correo);
        return participanteGrupoRepository.findByParticipante(participante);

    }
    public Organizacion traerOrganizacionDeAdministrador(String correo){
        if(esAdministradorEstructura(correo)){
            return traerAdministradorEstructuraDeCorreo(correo).getEstructura().getOrganizacion();
        }else if(esAdministradorOrganizacion(correo)){
            return traerAdministradorOrganizacionDeCorreo(correo).getOrganizacion();
        }
        return Organizacion.create();
    }
    public boolean esParticipante(String correo){
        return Objects.requireNonNull(usuarioRepository.findByUsername(correo).orElse(null)).getRol().equals(Rol.PARTICIPANTE);
    }
    public boolean esAdministradorEstructura(String correo){
        return Objects.requireNonNull(usuarioRepository.findByUsername(correo).orElse(null)).getRol().equals(Rol.ADMINISTRADOR_ESTRUCTURA);
    }
    public boolean esAdministradorOrganizacion(String correo){
        return Objects.requireNonNull(usuarioRepository.findByUsername(correo).orElse(null)).getRol().equals(Rol.ADMINISTRADOR_ORGANIZACION);
    }
    public boolean puedePublicarParticipante(String correo,Grupo grupo){
        ParticipanteGrupo participante = traerParticipanteGrupoDeCorreo(correo);
        return !UtilObject.isNull(participante) && participante.isActivo() && participante.isPuedePublicar() && participante.getGrupo().getIdentificador().equals(grupo.getIdentificador());
    }
    public boolean puedePublicar(String correo, Grupo grupo){
        if(esParticipante(correo) && puedePublicarParticipante(correo, grupo)){
            return true;
        }else return tienePermisos(correo, grupo.getEstructura());
    }
}

