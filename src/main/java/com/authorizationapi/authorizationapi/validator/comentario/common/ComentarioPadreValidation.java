package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonObjectValidator;
import uco.doo.rugrats.uconnect.dto.ComentarioDTO;

public class ComentarioPadreValidation implements Validation<ComentarioDTO>{
	
	private static final String BUSINESS_NAME = "comentario padre";
	
	private ComentarioPadreValidation() {
		super();
	}
	public static final Result validate(final ComentarioDTO data) {
		return new ComentarioPadreValidation().execute(data);
	}
	@Override
	public Result execute(ComentarioDTO data) {
		return CommonObjectValidator.excecute(data, ComentarioDTO.create(), BUSINESS_NAME);
	}
}
