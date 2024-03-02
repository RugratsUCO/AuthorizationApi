package com.authorizationapi.authorizationapi.domain.estructura;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;

import java.util.UUID;

public final class Estructura {
	private UUID identificador;
	private Organizacion organizacion;
	private Estructura estructuraPadre;
	private String nombre;
	private boolean estaActivo;
	private boolean tienePadre;
	private static final String UUID_PADRE = "";

	private static final Estructura PADRE = new Estructura(UtilUUID.generateUUIDFromString(UUID_PADRE),
			Organizacion.create(), null, UtilText.getDefaultValue(), UtilBoolean.getDefaultValue(),
			UtilBoolean.getDefaultValue());

	public Estructura(final UUID identificador, final Organizacion organizacion,
					  final Estructura estructuraPadre, final String nombre, final boolean estaActivo,
					  boolean tienePadre) {
		super();
		setIdentificador(identificador);
		setOrganizacion(organizacion);
		setEstructuraPadre(estructuraPadre);
		setNombre(nombre);
		setEstaActivo(estaActivo);
		setTienePadre(tienePadre);
	}

	private Estructura() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setOrganizacion(Organizacion.create());
		setEstructuraPadre(PADRE);
		setNombre(UtilText.getDefaultValue());
		setEstaActivo(UtilBoolean.getDefaultValue());
		setTienePadre(UtilBoolean.getDefaultValue());
	}

	public final boolean isTienePadre() {
		return tienePadre;
	}

	public Estructura setTienePadre(boolean tienePadre) {
		this.tienePadre = UtilBoolean.getDefault(tienePadre);
		return this;
	}

	private Estructura setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private Estructura setOrganizacion(final Organizacion organizacion) {
		this.organizacion = UtilObject.getDefault(organizacion, Organizacion.create());
		return this;
	}

	private Estructura setEstructuraPadre(final Estructura estructuraPadre) {
		if (isTienePadre()) {
			this.estructuraPadre = UtilObject.getDefault(estructuraPadre, Estructura.create());
		} else {
			this.estructuraPadre = PADRE;
		}
		return this;
	}

	private Estructura setNombre(final String nombre) {
		this.nombre = UtilText.applyTrim(nombre);
		return this;
	}

	private Estructura setEstaActivo(final boolean estaActivo) {
		this.estaActivo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public Estructura getEstructuraPadre() {
		return estructuraPadre;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean getEstaActivo() {
		return estaActivo;
	}

	public static Estructura create() {
		return new Estructura();
	}

}
