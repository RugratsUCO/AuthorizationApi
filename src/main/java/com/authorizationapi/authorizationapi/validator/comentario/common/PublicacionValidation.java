package uco.doo.rugrats.uconnect.api.validator.comentario.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonObjectValidator;
import uco.doo.rugrats.uconnect.dto.PublicacionDTO;

public class PublicacionValidation implements Validation<PublicacionDTO>{
	
	private static final String BUSINESS_NAME = "publicacion";
	
	private PublicacionValidation() {
		super();
	}
	public static final Result validate(final PublicacionDTO data) {
		return new PublicacionValidation().execute(data);
	}
	@Override
	public Result execute(PublicacionDTO data) {
		return CommonObjectValidator.excecute(data, PublicacionDTO.create(), BUSINESS_NAME);
	}
}