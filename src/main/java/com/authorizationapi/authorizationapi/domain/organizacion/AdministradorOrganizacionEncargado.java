package com.authorizationapi.authorizationapi.domain.organizacion;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.persona.Persona;

import java.util.UUID;

public final class AdministradorOrganizacionEncargado {
    private UUID identificador;
    private Persona persona;
    private boolean estaActivo;
    private Organizacion organizacion;

    private AdministradorOrganizacionEncargado(){
		super();
        setIdentificador(UtilUUID.getDefaultValue());
        setPersona(Persona.create());
        setEstaActivo(UtilBoolean.getDefaultValue());
        setOrganizacion(Organizacion.create());
    }

    public AdministradorOrganizacionEncargado(UUID identificador, Persona persona, boolean estaActivo, Organizacion organizacion){
        super();
        setIdentificador(identificador);
        setPersona(persona);
        setEstaActivo(estaActivo);
        setOrganizacion(organizacion);
    }

    private AdministradorOrganizacionEncargado setIdentificador(final UUID identificador) {
        this.identificador = UtilUUID.getDefault(identificador);
        return this;
    }

    private AdministradorOrganizacionEncargado setPersona(final Persona persona) {
        this.persona = UtilObject.getDefault(persona, Persona.create());
        return this;
    }
    public AdministradorOrganizacionEncargado setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
        return this;
    }
    public AdministradorOrganizacionEncargado setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
        return this;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Persona getPersona() {
        return persona;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }
    public Organizacion getOrganizacion() {
        return organizacion;
    }
    public static AdministradorOrganizacionEncargado create(){
        return new AdministradorOrganizacionEncargado();
    }
}
