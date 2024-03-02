package com.authorizationapi.authorizationapi.domain.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.persona.Persona;

import java.util.UUID;

public final class Participante {
	private UUID identificador;
	private Persona persona;
	private boolean estaActivo;

	private Participante() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setPersona(Persona.create());
		setEstaActivo(UtilBoolean.getDefaultValue());
	}

	public Participante(final UUID identificador, final Persona persona, final boolean estaActivo) {
		super();
		setIdentificador(identificador);
		setPersona(persona);
		setEstaActivo(estaActivo);
	}

	private Participante setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private Participante setPersona(final Persona persona) {
		this.persona = UtilObject.getDefault(persona, Persona.create());
		return this;
	}

	private Participante setEstaActivo(final boolean estaActivo) {
		this.estaActivo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public Persona getPersona() {
		return persona;
	}

	public boolean getEstaActivo() {
		return estaActivo;
	}

	public static Participante create() {
		return new Participante();
	}

}
