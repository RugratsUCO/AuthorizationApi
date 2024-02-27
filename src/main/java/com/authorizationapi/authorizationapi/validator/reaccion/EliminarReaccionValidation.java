package uco.doo.rugrats.uconnect.api.validator.reaccion;

import java.util.UUID;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.IdentificadorValidation;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class EliminarReaccionValidation implements Validation<UUID>{
	public static final Result validate(final UUID data) {
		return new EliminarReaccionValidation().execute(data);
	}
	private EliminarReaccionValidation() {
		super();
	}
	
	@Override
	public Result execute(final UUID data) {
		var result = Result.create();
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible eliminar la reacción");
		}else {
			result.addMessages(IdentificadorValidation.validate(data).getMessages());
			
		}
		return result;
		
	}

}
