package com.authorizationapi.authorizationapi.domain.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "Participantegrupo")
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

	public static ParticipanteGrupo create() {
		return new ParticipanteGrupo();
	}

}
