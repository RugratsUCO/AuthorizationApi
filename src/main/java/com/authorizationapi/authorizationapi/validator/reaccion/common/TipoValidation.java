package uco.doo.rugrats.uconnect.api.validator.reaccion.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonObjectValidator;
import uco.doo.rugrats.uconnect.dto.TipoReaccionDTO;

public class TipoValidation implements Validation<TipoReaccionDTO> {
	private static final String BUSINESS_NAME = "tipo de reaccion";
	
	private TipoValidation() {
		super();
	}
	public static final Result validate(final TipoReaccionDTO data) {
		return new TipoValidation().execute(data);
	}
	@Override
	public Result execute(TipoReaccionDTO data) {
		return CommonObjectValidator.excecute(data, TipoReaccionDTO.create(), BUSINESS_NAME);
	}

}
