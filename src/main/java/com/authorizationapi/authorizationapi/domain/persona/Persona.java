package com.authorizationapi.authorizationapi.domain.persona;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class Persona {
	private UUID identificador;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String correo;
	private String paisTelefono;
	private String numeroTelefono;
	private boolean estaActivo;


	private Persona() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setTipoIdentificacion(UtilText.getDefaultValue());
		setNumeroIdentificacion(UtilText.getDefaultNumeric());
		setPrimerNombre(UtilText.getDefaultValue());
		setSegundoNombre(UtilText.getDefaultValue());
		setPrimerApellido(UtilText.getDefaultValue());
		setSegundoApellido(UtilText.getDefaultValue());
		setCorreo(UtilText.getDefaultEmailAdress());
		setPaisTelefono(UtilText.getDefaultValue());
		setNumeroTelefono(UtilText.getDefaultNumeric());
		setEstaActivo(UtilBoolean.getDefaultValue());
	}

	public Persona(UUID identificador, String tipoIdentificacion,
				   String numeroIdentificacion, String primerNombre, String segundoNombre,
				   String primerApellido, String segundoApellido, String correo,
				   String paisTelefono, String numeroTelefono, boolean estaActivo) {
		super();
		setIdentificador(identificador);
		setTipoIdentificacion(tipoIdentificacion);
		setNumeroIdentificacion(numeroIdentificacion);
		setPrimerNombre(primerNombre);
		setSegundoNombre(segundoNombre);
		setPrimerApellido(primerApellido);
		setSegundoApellido(segundoApellido);
		setCorreo(correo);
		setPaisTelefono(paisTelefono);
		setNumeroTelefono(numeroTelefono);
		setEstaActivo(estaActivo);
	}

	public Persona setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	private Persona setTipoIdentificacion(final String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
		return this;
	}

	private Persona setNumeroIdentificacion(final String numeroIdentificacion) {
		this.numeroIdentificacion = UtilText.getUtilText().numericIsValid(UtilText.applyTrim(numeroIdentificacion))
				? UtilText.applyTrim(numeroIdentificacion)
				: UtilText.getDefaultNumeric();
		return this;
	}

	private Persona setPrimerNombre(String primerNombre) {
		this.primerNombre = UtilText.applyTrim(primerNombre);
		return this;
	}

	private Persona setSegundoNombre(String segundoNombre) {
		this.segundoNombre = UtilText.applyTrim(segundoNombre);
		return this;
	}

	private Persona setPrimerApellido(String primerApellido) {
		this.primerApellido = UtilText.applyTrim(primerApellido);
		return this;
	}

	private Persona setSegundoApellido(String segundoApellido) {
		this.segundoApellido = UtilText.applyTrim(segundoApellido);
		return this;
	}

	private Persona setCorreo(final String correo) {
		this.correo = UtilText.getUtilText().emailIsvalid(UtilText.applyTrim(correo)) ? UtilText.applyTrim(correo)
				: UtilText.getDefaultEmailAdress();
		return this;
	}

	private Persona setPaisTelefono(final String paisTelefono) {
		this.paisTelefono = paisTelefono;
		return this;
	}

	private Persona setNumeroTelefono(final String numeroTelefono) {
		this.numeroTelefono = UtilText.getUtilText().numericIsValid(UtilText.applyTrim(numeroTelefono))
				? UtilText.applyTrim(numeroTelefono)
				: UtilText.getDefaultNumeric();
		return this;
	}

	private Persona setEstaActivo(final boolean estaActivo) {
		this.estaActivo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
		return this;
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public String getPaisTelefono() {
		return paisTelefono;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public boolean getEstaActivo() {
		return estaActivo;
	}

	public static Persona create() {
		return new Persona();
	}
}
