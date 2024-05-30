package com.authorizationapi.authorizationapi.domain.persona;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Persona")
public final class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "identificador",nullable = false)
	private UUID identificador;
	@Column(name = "activo")
	private boolean activo;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	public Persona() {
		super();
		setUsuario(new Usuario());
		setIdentificador(UtilUUID.getDefaultValue());
		setActivo(UtilBoolean.getDefaultValue());
	}

	public Persona(UUID identificador, boolean estaActivo, Usuario usuario) {
		super();
		setUsuario(usuario);
		setIdentificador(identificador);
		setActivo(estaActivo);
	}

	public Persona setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}
	public Persona setActivo(final boolean activo) {
		this.activo = UtilObject.getDefault(activo, UtilBoolean.getDefaultValue());
		return this;
	}
	public Persona setUsuario(final Usuario usuario) {
		this.usuario = UtilObject.getDefault(usuario, new Usuario());
		return this;
	}
	public UUID getIdentificador() {
		return identificador;
	}

	public boolean getActivo() {
		return activo;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public static Persona create() {
		return new Persona();
	}
}
