package com.authorizationapi.authorizationapi.domain.estructura;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.persona.Persona;
import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "Administradorestructuraencargado")
public final class AdministradorEstructuraEncargado {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "identificador", nullable = false)
	private UUID identificador;

	@ManyToOne
	@JoinColumn(name = "persona")
	private Persona persona;

	@Column(name = "activo")
	private boolean activo;

	@ManyToOne
	@JoinColumn(name = "estructura")
	private Estructura estructura;

	public AdministradorEstructuraEncargado() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setPersona(Persona.create());
		setActivo(UtilBoolean.getDefaultValue());
		setEstructura(Estructura.create());
	}

	public AdministradorEstructuraEncargado(UUID identificador, Persona persona, boolean estaActivo, Estructura estructura) {
		super();
		setIdentificador(identificador);
		setPersona(persona);
		setActivo(estaActivo);
		setEstructura(estructura);
	}

	public AdministradorEstructuraEncargado setIdentificador(UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	public AdministradorEstructuraEncargado setPersona(Persona persona) {
		this.persona = UtilObject.getDefault(persona, Persona.create());
		return this;
	}
	public AdministradorEstructuraEncargado setEstructura(Estructura estructura) {
		this.estructura = estructura;
		return this;
	}
	public AdministradorEstructuraEncargado setActivo(boolean estaActivo) {
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

	public Estructura getEstructura() {
		return estructura;
	}

	public static AdministradorEstructuraEncargado create() {
		return new AdministradorEstructuraEncargado();
	}

}
