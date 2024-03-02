package com.authorizationapi.authorizationapi.domain.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class ParticipanteGrupo {
	private UUID identificador;
	private Participante participante;
	private Grupo grupo;
	private Boolean puedePublicar;
	private boolean estaActivo;


	private ParticipanteGrupo() {
		setIdentificador(UtilUUID.getDefaultValue());
		setParticipante(Participante.create());
		setPuedePublicar(UtilBoolean.getDefaultValue());
		setGrupo(Grupo.create());
		setEstaActivo(UtilBoolean.getDefaultValue());
	}

	public ParticipanteGrupo(final UUID identificador, final Participante participante,
							 final Boolean puedePublicar, final Grupo grupo, final boolean estaActivo) {
		super();
		setIdentificador(identificador);
		setParticipante(participante);
		setPuedePublicar(puedePublicar);
		setGrupo(grupo);
		setEstaActivo(estaActivo);
	}

	private ParticipanteGrupo setPuedePublicar(final Boolean puedePublicar) {
		this.puedePublicar = UtilBoolean.getDefault(puedePublicar);
		return this;
	}

	private ParticipanteGrupo setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private ParticipanteGrupo setParticipante(final Participante participante) {
		this.participante = UtilObject.getDefault(participante, Participante.create());
		return this;
	}

	private ParticipanteGrupo setGrupo(final Grupo grupo) {
		this.grupo = UtilObject.getDefault(grupo, Grupo.create());
		return this;
	}

	private ParticipanteGrupo setEstaActivo(final boolean estaActivo) {
		this.estaActivo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
	}

	public Boolean getPuedePublicar() {
		return puedePublicar;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public Participante getParticipante() {
		return participante;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public boolean getEstaActivo() {
		return estaActivo;
	}

	public static ParticipanteGrupo create() {
		return new ParticipanteGrupo();
	}

}
