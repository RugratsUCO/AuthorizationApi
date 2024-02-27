package com.authorizationapi.authorizationapi.domain;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilObject;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Document(collection = "administrador_estructura_encargado_data")
public final class AdministradorEstructuraEncargadoDomain {
	@Id
	private UUID identificador;
	private PersonaDomain persona;
	public static final AdministradorEstructuraEncargadoDomain DEFAULT_OBJECT = new AdministradorEstructuraEncargadoDomain();

	private AdministradorEstructuraEncargadoDomain() {
		super();
		setIdentificador(UtilUUID.getDefaultValue());
		setPersona(PersonaDomain.getDefaultObject());
	}

	public AdministradorEstructuraEncargadoDomain(final UUID identificador, final PersonaDomain persona) {
		super();
		setIdentificador(identificador);
		setPersona(persona);
	}

	private void setIdentificador(UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	private void setPersona(PersonaDomain persona) {
		this.persona = UtilObject.getDefault(persona, PersonaDomain.getDefaultObject());
	}

	public UUID getIdentificador() {
		return identificador;
	}

	public PersonaDomain getPersona() {
		return persona;
	}

	public static AdministradorEstructuraEncargadoDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}

	@Override
	public String toString() {
		return "AdministradorEstructuraEncargado{" +
				"identificador='" + identificador + '\'' +
				", persona='" + persona  + '\'' +
				'}';
	}
}
