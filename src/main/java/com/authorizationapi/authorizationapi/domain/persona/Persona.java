package com.authorizationapi.authorizationapi.domain.persona;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
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

	@Column(name = "tipoIdentificacion")
	private String tipoIdentificacion;

	@Column(name = "numeroIdentificacion")
	private String numeroIdentificacion;

	@Column(name = "primerNombre")
	private String primerNombre;

	@Column(name = "segundoNombre")
	private String segundoNombre;

	@Column(name = "primerApellido")
	private String primerApellido;

	@Column(name = "segundoApellido")
	private String segundoApellido;

	@Column(name = "correo")
	private String correo;

	@Column(name = "paisTelefono")
	private String paisTelefono;

	@Column(name = "numeroTelefono")
	private String numeroTelefono;

	@Column(name = "activo")
	private boolean activo;

	public Persona() {
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
		setActivo(UtilBoolean.getDefaultValue());
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
		setActivo(estaActivo);
	}

	public Persona setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
		return this;
	}

	public Persona setTipoIdentificacion(final String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
		return this;
	}

	public Persona setNumeroIdentificacion(final String numeroIdentificacion) {
		this.numeroIdentificacion = UtilText.getUtilText().numericIsValid(UtilText.applyTrim(numeroIdentificacion))
				? UtilText.applyTrim(numeroIdentificacion)
				: UtilText.getDefaultNumeric();
		return this;
	}

	public Persona setPrimerNombre(String primerNombre) {
		this.primerNombre = UtilText.applyTrim(primerNombre);
		return this;
	}

	public Persona setSegundoNombre(String segundoNombre) {
		this.segundoNombre = UtilText.applyTrim(segundoNombre);
		return this;
	}

	public Persona setPrimerApellido(String primerApellido) {
		this.primerApellido = UtilText.applyTrim(primerApellido);
		return this;
	}

	public Persona setSegundoApellido(String segundoApellido) {
		this.segundoApellido = UtilText.applyTrim(segundoApellido);
		return this;
	}

	public Persona setCorreo(final String correo) {
		this.correo = UtilText.getUtilText().emailIsvalid(UtilText.applyTrim(correo)) ? UtilText.applyTrim(correo)
				: UtilText.getDefaultEmailAdress();
		return this;
	}

	public Persona setPaisTelefono(final String paisTelefono) {
		this.paisTelefono = paisTelefono;
		return this;
	}

	public Persona setNumeroTelefono(final String numeroTelefono) {
		this.numeroTelefono = UtilText.getUtilText().numericIsValid(UtilText.applyTrim(numeroTelefono))
				? UtilText.applyTrim(numeroTelefono)
				: UtilText.getDefaultNumeric();
		return this;
	}

	public Persona setActivo(final boolean activo) {
		this.activo = UtilObject.getDefault(activo, UtilBoolean.getDefaultValue());
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

	public boolean getActivo() {
		return activo;
	}

	public static Persona create() {
		return new Persona();
	}
}
