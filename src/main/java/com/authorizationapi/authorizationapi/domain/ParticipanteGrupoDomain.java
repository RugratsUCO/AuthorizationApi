package com.authorizationapi.authorizationapi.domain;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class ParticipanteGrupoDomain {
	private UUID identificador;
	private ParticipanteDomain participante;
	private GrupoDomain grupo;
	private Boolean puedePublicar;
	private EstadoDomain estado;
	private boolean estaEstadoRealActivo;

	public static final ParticipanteGrupoDomain DEFAULT_OBJECT = new ParticipanteGrupoDomain();

	private ParticipanteGrupoDomain() {
		setIdentificador(UtilUUID.getDefaultValue());
		setParticipante(ParticipanteDomain.getDefaultObject());
		setPuedePublicar(UtilBoolean.getDefaultValue());
		setGrupo(GrupoDomain.getDefaultObject());
		setEstado(EstadoDomain.getDefaultObject());
		setEstaEstadoRealActivo(UtilBoolean.getDefaultValue());
	}

	public ParticipanteGrupoDomain(final UUID identificador, final ParticipanteDomain participante,
			final Boolean puedePublicar, final GrupoDomain grupo, final EstadoDomain estado,final boolean estaEstadoRealActivo) {
		super();
		setIdentificador(identificador);
		setParticipante(participante);
		setPuedePublicar(puedePublicar);
		setGrupo(grupo);
		setEstado(estado); 
		setEstaEstadoRealActivo(estaEstadoRealActivo);
	}

	private void setPuedePublicar(final Boolean puedePublicar) {
		this.puedePublicar = UtilBoolean.getDefault(puedePublicar);
	}

	private void setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	private void setParticipante(final ParticipanteDomain participante) {
		this.participante = UtilObject.getDefault(participante, ParticipanteDomain.getDefaultObject());
	}

	private void setGrupo(final GrupoDomain grupo) {
		this.grupo = UtilObject.getDefault(grupo, GrupoDomain.getDefaultObject());
	}

	private void setEstado(final EstadoDomain estado) {
		this.estado = UtilObject.getDefault(estado, EstadoDomain.getDefaultObject());
	}

	public Boolean getPuedePublicar() {
		return puedePublicar;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public ParticipanteDomain getParticipante() {
		return participante;
	}

	public GrupoDomain getGrupo() {
		return grupo;
	}

	public EstadoDomain getEstado() {
		return estado;
	}

	public static ParticipanteGrupoDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}

	public boolean isEstaEstadoRealActivo() {
		return estaEstadoRealActivo;
	}

	private void setEstaEstadoRealActivo(boolean estaEstadoRealActivo) {
		this.estaEstadoRealActivo = estaEstadoRealActivo;
	}
}
