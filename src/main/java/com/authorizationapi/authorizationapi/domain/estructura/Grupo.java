package com.authorizationapi.authorizationapi.domain.estructura;


import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class Grupo {
	private UUID identificador;
	private Estructura estructura;
	private String nombre;
	private boolean estaActivo;

	private Grupo() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setEstructura(Estructura.create());
		setNombre(UtilText.getDefaultValue());
		setEstaActivo(UtilBoolean.getDefaultValue());
	}

	public Grupo(final UUID identificador, final Estructura estructura, final String nombre,
				 final boolean estaActivo) {
		super();
		setIdentificador(identificador);
		setEstructura(estructura);
		setNombre(nombre);
		setEstaActivo(estaActivo);

	}

	private Grupo setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private Grupo setEstructura(final Estructura estructura) {
		this.estructura = UtilObject.getDefault(estructura, Estructura.create());
		return this;
	}

	private Grupo setNombre(final String nombre) {
		this.nombre = UtilText.applyTrim(nombre);
		return this;
	}

	private Grupo setEstaActivo(final boolean estaActivo) {
		this.estaActivo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
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

	public boolean getEstaActivo() {
		return estaActivo;
	}

	public static Grupo create() {
		return new Grupo();
	}

}
