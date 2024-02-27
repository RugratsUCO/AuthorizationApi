package com.authorizationapi.authorizationapi.domain;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class ParticipanteDomain {
	private UUID identificador;
	private PersonaDomain persona;
	private EstadoDomain estado;
	private boolean estaEstadoRealActivo;

	public static final ParticipanteDomain DEFAULT_OBJECT = new ParticipanteDomain();

	private ParticipanteDomain() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setPersona(PersonaDomain.getDefaultObject());
		setEstado(EstadoDomain.getDefaultObject());
		setEstaEstadoRealActivo(UtilBoolean.getDefaultValue());
	}

	public ParticipanteDomain(final UUID identificador, final PersonaDomain persona, final EstadoDomain estado,final boolean estaEstadoRealActivo) {
		super();
		setIdentificador(identificador);
		setPersona(persona);
		setEstado(estado);
		setEstaEstadoRealActivo(estaEstadoRealActivo);
	}

	private void setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	private void setPersona(final PersonaDomain persona) {
		this.persona = UtilObject.getDefault(persona, PersonaDomain.getDefaultObject());
	}

	private void setEstado(final EstadoDomain estado) {
		this.estado = UtilObject.getDefault(estado, EstadoDomain.getDefaultObject());
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public PersonaDomain getPersona() {
		return persona;
	}

	public EstadoDomain getEstado() {
		return estado;
	}

	public static ParticipanteDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}

	public boolean isEstaEstadoRealActivo() {
		return estaEstadoRealActivo;
	}

	private void setEstaEstadoRealActivo(boolean estaEstadoRealActivo) {
		this.estaEstadoRealActivo = estaEstadoRealActivo;
	}
}
