package com.authorizationapi.authorizationapi.domain.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.persona.Persona;

import java.util.UUID;

public final class AdministradorEstructuraEncargado {
	private UUID identificador;
	private Persona persona;

	private boolean estaActivo;
	private Estructura estructura;

	private AdministradorEstructuraEncargado() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setPersona(Persona.create());
		setEstaActivo(UtilBoolean.getDefaultValue());
		setEstructura(Estructura.create());
	}

	public AdministradorEstructuraEncargado(UUID identificador, Persona persona, boolean estaActivo, Estructura estructura) {
		super();
		setIdentificador(identificador);
		setPersona(persona);
		setEstaActivo(estaActivo);
		setEstructura(estructura);
	}

	private AdministradorEstructuraEncargado setIdentificador(UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private AdministradorEstructuraEncargado setPersona(Persona persona) {
		this.persona = UtilObject.getDefault(persona, Persona.create());
		return this;
	}
	public AdministradorEstructuraEncargado setEstructura(Estructura estructura) {
		this.estructura = estructura;
		return this;
	}
	public AdministradorEstructuraEncargado setEstaActivo(boolean estaActivo) {
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

	public Estructura getEstructura() {
		return estructura;
	}

	public static AdministradorEstructuraEncargado create() {
		return new AdministradorEstructuraEncargado();
	}

}
