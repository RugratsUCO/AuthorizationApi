package uco.doo.rugrats.uconnect.api.validator.reaccion.common;

import java.time.LocalDateTime;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonDateValidator;

public class FechaReaccionValidation implements Validation<LocalDateTime>{
	private FechaReaccionValidation() {
		super();
	}
	public static final Result validate(final LocalDateTime data) {
		return new FechaReaccionValidation().execute(data);
	}
	@Override
	public Result execute(LocalDateTime data) {
		return CommonDateValidator.execute(data);
	}
}
