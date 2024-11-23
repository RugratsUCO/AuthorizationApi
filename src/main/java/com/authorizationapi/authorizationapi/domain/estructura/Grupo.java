package com.authorizationapi.authorizationapi.domain.estructura;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "Grupo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "identificador", nullable = false)
	private UUID identificador;

	@ManyToOne
	@JoinColumn(name = "estructura")
	private Estructura estructura;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "activo")
	private boolean activo;

	public Grupo() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setEstructura(Estructura.create());
		setNombre(UtilText.getDefaultValue());
		setActivo(UtilBoolean.getDefaultValue());
	}

	public Grupo(final UUID identificador, final Estructura estructura, final String nombre,
				 final boolean estaActivo) {
		super();
		setIdentificador(identificador);
		setEstructura(estructura);
		setNombre(nombre);
		setActivo(estaActivo);

	}

	public Grupo setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	public Grupo setEstructura(final Estructura estructura) {
		this.estructura = UtilObject.getDefault(estructura, Estructura.create());
		return this;
	}

	public Grupo setNombre(final String nombre) {
		this.nombre = UtilText.applyTrim(nombre);
		return this;
	}

	public Grupo setActivo(final boolean estaActivo) {
		this.activo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public Estructura getEstructura() {
		return estructura;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean getActivo() {
		return activo;
	}

	public static Grupo create() {
		return new Grupo();
	}

}
