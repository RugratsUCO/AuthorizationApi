package com.authorizationapi.authorizationapi.domain;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;

import java.util.UUID;

public final class PersonaDomain {
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
	private EstadoDomain estado;

	public static final PersonaDomain DEFAULT_OBJECT = new PersonaDomain();

	private PersonaDomain() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setTipoIdentificacion("");
		setNumeroIdentificacion(UtilText.getDefaultNumeric());
		setPrimerNombre(UtilText.getDefaultValue());
		setSegundoNombre(UtilText.getDefaultValue());
		setPrimerApellido(UtilText.getDefaultValue());
		setSegundoApellido(UtilText.getDefaultValue());
		setCorreo(UtilText.getDefaultEmailAdress());
		setPaisTelefono("");
		setNumeroTelefono(UtilText.getDefaultNumeric());
		setEstado(EstadoDomain.getDefaultObject());
	}

	public PersonaDomain(UUID identificador, String tipoIdentificacion,
			String numeroIdentificacion, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String correo,
			String paisTelefono, String numeroTelefono, EstadoDomain estado) {
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
		setEstado(estado);
	}

	public void setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	private void setTipoIdentificacion(final String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	private void setNumeroIdentificacion(final String numeroIdentificacion) {
		this.numeroIdentificacion = UtilText.getUtilText().numericIsValid(UtilText.applyTrim(numeroIdentificacion))
				? UtilText.applyTrim(numeroIdentificacion)
				: UtilText.getDefaultNumeric();
	}

	private void setPrimerNombre(String primerNombre) {
		this.primerNombre = UtilText.applyTrim(primerNombre);
	}

	private void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = UtilText.applyTrim(segundoNombre);
	}

	private void setPrimerApellido(String primerApellido) {
		this.primerApellido = UtilText.applyTrim(primerApellido);
	}

	private void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = UtilText.applyTrim(segundoApellido);
	}

	private void setCorreo(final String correo) {
		this.correo = UtilText.getUtilText().emailIsvalid(UtilText.applyTrim(correo)) ? UtilText.applyTrim(correo)
				: UtilText.getDefaultEmailAdress();
	}

	private void setPaisTelefono(final String paisTelefono) {
		this.paisTelefono = paisTelefono;
	}

	private void setNumeroTelefono(final String numeroTelefono) {
		this.numeroTelefono = UtilText.getUtilText().numericIsValid(UtilText.applyTrim(numeroTelefono))
				? UtilText.applyTrim(numeroTelefono)
				: UtilText.getDefaultNumeric();
	}

	private void setEstado(final EstadoDomain estado) {
		this.estado = UtilObject.getDefault(estado, EstadoDomain.getDefaultObject());
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

	public EstadoDomain getEstado() {
		return estado;
	}

	public static PersonaDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}
}
