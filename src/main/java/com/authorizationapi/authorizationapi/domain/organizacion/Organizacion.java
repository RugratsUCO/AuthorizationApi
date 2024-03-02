package com.authorizationapi.authorizationapi.domain.organizacion;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class Organizacion {
	private UUID identificador;
	private String nombre;
	private String descripcion;
	private String tipo;
	private boolean estaActivo;


	private Organizacion() {
		setIdentificador(UtilUUID.getDefaultValue());
		setNombre(UtilText.getDefaultValue());
		setDescripcion(UtilText.getDefaultValue());
		setTipo(UtilText.getDefaultValue());
		setEstaActivo(UtilBoolean.getDefaultValue());
	}

	public Organizacion(final UUID identificador, final String nombre, final String descripcion,
						final String tipo, final boolean estaActivo) {
		setIdentificador(identificador);
		setNombre(nombre);
		setDescripcion(descripcion);
		setTipo(tipo);
		setEstaActivo(estaActivo);
	}

	private Organizacion setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private Organizacion setNombre(final String nombre) {
		this.nombre = UtilText.applyTrim(nombre);
		return this;
	}

	private Organizacion setDescripcion(final String descripcion) {
		this.descripcion = UtilText.applyTrim(descripcion);
		return this;
	}

	private Organizacion setTipo(final String tipo) {
		this.tipo = tipo;
		return this;
	}

	private Organizacion setEstaActivo(final boolean estaActivo) {
		this.estaActivo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean getEstaActivo() {
		return estaActivo;
	}

	public static Organizacion create() {
		return new Organizacion();
	}
}
