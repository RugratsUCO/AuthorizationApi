package com.authorizationapi.authorizationapi.domain;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class AdministradorOrganizacionEncargadoDomain {
    private UUID identificador;
    private PersonaDomain persona;

    public static final AdministradorOrganizacionEncargadoDomain DEFAULT_OBJECT = new AdministradorOrganizacionEncargadoDomain();
    private AdministradorOrganizacionEncargadoDomain(){
		super();
        setIdentificador(UtilUUID.getDefaultValue());
        setPersona(PersonaDomain.getDefaultObject());
    }

    public AdministradorOrganizacionEncargadoDomain(final UUID identificador, final PersonaDomain persona){
        super();
        setIdentificador(identificador);
        setPersona(persona);
    }

    private void setIdentificador(final UUID identificador) {
        this.identificador = UtilUUID.getDefault(identificador);
    }

    private void setPersona(final PersonaDomain persona) {
        this.persona = UtilObject.getDefault(persona, PersonaDomain.getDefaultObject());
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public PersonaDomain getPersona() {
        return persona;
    }

    public static AdministradorOrganizacionEncargadoDomain getDefaultObject(){
        return DEFAULT_OBJECT;
    }
}
