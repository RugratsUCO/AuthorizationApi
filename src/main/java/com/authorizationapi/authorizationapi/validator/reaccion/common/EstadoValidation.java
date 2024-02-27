package uco.doo.rugrats.uconnect.api.validator.reaccion.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.common.CommonObjectValidator;
import uco.doo.rugrats.uconnect.dto.EstadoDTO;

public class EstadoValidation implements Validation<EstadoDTO>{
	
	private static final String BUSINESS_NAME = "estado";
	
	private EstadoValidation() {
		super();
	}
	public static final Result validate(final EstadoDTO data) {
		return new EstadoValidation().execute(data);
	}
	@Override
	public Result execute(EstadoDTO data) {
		return CommonObjectValidator.excecute(data, EstadoDTO.create(), BUSINESS_NAME);
	}
}