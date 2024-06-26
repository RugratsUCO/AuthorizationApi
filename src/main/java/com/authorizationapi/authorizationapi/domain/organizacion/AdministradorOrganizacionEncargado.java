package com.authorizationapi.authorizationapi.domain.organizacion;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.persona.Persona;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Administradororganizacionencargado")
public final class AdministradorOrganizacionEncargado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador",nullable = false)
    private UUID identificador;

    @ManyToOne
    @JoinColumn(name = "persona")
    private Persona persona;

    @Column(name = "activo")
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "organizacion")
    private Organizacion organizacion;

    public AdministradorOrganizacionEncargado(){
		super();
        setIdentificador(UtilUUID.getDefaultValue());
        setPersona(Persona.create());
        setActivo(UtilBoolean.getDefaultValue());
        setOrganizacion(Organizacion.create());
    }

    public AdministradorOrganizacionEncargado(UUID identificador, Persona persona, boolean estaActivo, Organizacion organizacion){
        super();
        setIdentificador(identificador);
        setPersona(persona);
        setActivo(estaActivo);
        setOrganizacion(organizacion);
    }

    public AdministradorOrganizacionEncargado setIdentificador(final UUID identificador) {
        this.identificador = identificador;
        return this;
    }

    public AdministradorOrganizacionEncargado setPersona(final Persona persona) {
        this.persona = UtilObject.getDefault(persona, Persona.create());
        return this;
    }
    public AdministradorOrganizacionEncargado setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
        return this;
    }
    public AdministradorOrganizacionEncargado setActivo(boolean estaActivo) {
        this.activo = estaActivo;
        return this;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Persona getPersona() {
        return persona;
    }

    public boolean isActivo() {
        return activo;
    }
    public Organizacion getOrganizacion() {
        return organizacion;
    }
    public static AdministradorOrganizacionEncargado create(){
        return new AdministradorOrganizacionEncargado();
    }
}
