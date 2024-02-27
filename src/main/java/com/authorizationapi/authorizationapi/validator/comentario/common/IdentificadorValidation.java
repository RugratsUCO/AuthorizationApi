package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import java.util.UUID;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonUUIDValidator;

public class IdentificadorValidation implements Validation<UUID>{
	private IdentificadorValidation() {
		super();
	}
	
	public static final Result validate(final UUID data) {
		return new IdentificadorValidation().execute(data);
	}
	
	@Override
	public Result execute(UUID data) {
		return CommonUUIDValidator.execute(data);
	}
}
