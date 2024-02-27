package com.authorizationapi.authorizationapi.domain;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class OrganizacionDomain {
	private UUID identificador;
	private String nombre;
	private String descripcion;
	private String tipo;
	private EstadoDomain estado;

	public static final OrganizacionDomain DEFAULT_OBJECT = new OrganizacionDomain();

	private OrganizacionDomain() {
		setIdentificador(UtilUUID.getDefaultValue());
		setNombre(UtilText.getDefaultValue());
		setDescripcion(UtilText.getDefaultValue());
		setTipo("");
		setEstado(EstadoDomain.getDefaultObject());
	}

	public OrganizacionDomain(final UUID identificador, final String nombre, final String descripcion,
			final String tipo, final EstadoDomain estado) {
		setIdentificador(identificador);
		setNombre(nombre);
		setDescripcion(descripcion);
		setTipo(tipo);
		setEstado(estado);
	}

	private void setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	private void setNombre(final String nombre) {
		this.nombre = UtilText.applyTrim(nombre);
	}

	private void setDescripcion(final String descripcion) {
		this.descripcion = UtilText.applyTrim(descripcion);
	}

	private void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	private void setEstado(final EstadoDomain estado) {
		this.estado = UtilObject.getDefault(estado, EstadoDomain.getDefaultObject());
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

	public EstadoDomain getEstado() {
		return estado;
	}

	public static OrganizacionDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}
}
