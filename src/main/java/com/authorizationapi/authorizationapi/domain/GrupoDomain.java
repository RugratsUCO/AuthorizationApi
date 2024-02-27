package com.authorizationapi.authorizationapi.domain;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class GrupoDomain {
	private UUID identificador;
	private EstructuraDomain estructura;
	private String nombre;
	private EstadoDomain estado;
	private boolean estaEstadoRealActivo;
	public static final GrupoDomain DEFAULT_OBJECT = new GrupoDomain();

	private GrupoDomain() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setEstructura(EstructuraDomain.getDefaultObject());
		setNombre(UtilText.getDefaultValue());
		setEstado(EstadoDomain.getDefaultObject());
		setEstaEstadoRealActivo(UtilBoolean.getDefaultValue());
	}

	public GrupoDomain(final UUID identificador, final EstructuraDomain estructura, final String nombre,
			final EstadoDomain estado,final boolean estaEstadoRealActivo) {
		super();
		setIdentificador(identificador);
		setEstructura(estructura);
		setNombre(nombre);
		setEstado(estado);
		setEstaEstadoRealActivo(estaEstadoRealActivo);
	}

	private void setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	private void setEstructura(final EstructuraDomain estructura) {
		this.estructura = UtilObject.getDefault(estructura, EstructuraDomain.getDefaultObject());
	}

	private void setNombre(final String nombre) {
		this.nombre = UtilText.applyTrim(nombre);
	}

	private void setEstado(final EstadoDomain estado) {
		this.estado = UtilObject.getDefault(estado, EstadoDomain.getDefaultObject());
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public EstructuraDomain getEstructura() {
		return estructura;
	}

	public String getNombre() {
		return nombre;
	}

	public EstadoDomain getEstado() {
		return estado;
	}

	public static GrupoDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}

	public boolean isEstaEstadoRealActivo() {
		return estaEstadoRealActivo;
	}

	private void setEstaEstadoRealActivo(boolean estaEstadoRealActivo) {
		this.estaEstadoRealActivo = estaEstadoRealActivo;
	}
}
