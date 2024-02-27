package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import java.time.LocalDateTime;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonDateValidator;

public class FechaPublicacionValidation implements Validation<LocalDateTime>{
	private FechaPublicacionValidation() {
		super();
	}
	public static final Result validate(final LocalDateTime data) {
		return new FechaPublicacionValidation().execute(data);
	}
	@Override
	public final Result execute(final LocalDateTime data) {
		return CommonDateValidator.execute(data);
	}
}
