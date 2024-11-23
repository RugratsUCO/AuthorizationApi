package com.authorizationapi.authorizationapi.domain.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "Participantegrupo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ParticipanteGrupo {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "identificador", nullable = false)
	private UUID identificador;

	@ManyToOne
	@JoinColumn(name = "participante")
	private Participante participante;

	@ManyToOne
	@JoinColumn(name = "grupo")
	private Grupo grupo;

	@Column(name = "activo")
	private boolean activo;

	@Column(name = "puedePublicar")
	private boolean puedePublicar;

	public ParticipanteGrupo() {
		setIdentificador(UtilUUID.getDefaultValue());
		setParticipante(Participante.create());
		setGrupo(Grupo.create());
		setActivo(UtilBoolean.getDefaultValue());
	}

	public ParticipanteGrupo(final UUID identificador, final Participante participante,
							  final Grupo grupo, final boolean estaActivo) {
		super();
		setIdentificador(identificador);
		setParticipante(participante);
		setGrupo(grupo);
		setActivo(estaActivo);
	}

	public ParticipanteGrupo setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	public ParticipanteGrupo setParticipante(final Participante participante) {
		this.participante = UtilObject.getDefault(participante, Participante.create());
		return this;
	}

	public ParticipanteGrupo setGrupo(final Grupo grupo) {
		this.grupo = UtilObject.getDefault(grupo, Grupo.create());
		return this;
	}

	public ParticipanteGrupo setActivo(final boolean estaActivo) {
		this.activo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
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

	public boolean isActivo() {
		return activo;
	}

	public boolean isPuedePublicar() {
		return puedePublicar;
	}

	public ParticipanteGrupo setPuedePublicar(boolean puedePublicar) {
		this.puedePublicar = UtilObject.getDefault(puedePublicar, UtilBoolean.getDefaultValue());
		return this;
	}

	public static ParticipanteGrupo create() {
		return new ParticipanteGrupo();
	}

}
