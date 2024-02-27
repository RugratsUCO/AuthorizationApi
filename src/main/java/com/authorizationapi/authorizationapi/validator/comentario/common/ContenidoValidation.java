package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonTextValidator;

public class ContenidoValidation implements Validation<String> {

	private static final int MINIMUN_LENGHT = 1;
	private static final int MAXIMUN_LENGHT = 250;

	private ContenidoValidation() {
		super();
	}

	public static final Result validate(final String data) {
		return new ContenidoValidation().execute(data);
	}

	@Override
	public Result execute(String data) {
		return CommonTextValidator.execute(data, MINIMUN_LENGHT, MAXIMUN_LENGHT);
	}

}
