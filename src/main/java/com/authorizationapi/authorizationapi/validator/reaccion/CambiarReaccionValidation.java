package uco.doo.rugrats.uconnect.api.validator.reaccion;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.IdentificadorValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.TipoValidation;
import uco.doo.rugrats.uconnect.dto.ReaccionDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class CambiarReaccionValidation implements Validation<ReaccionDTO>{
	public static final Result validate(final ReaccionDTO data) {
		return new CambiarReaccionValidation().execute(data);
	}
	private CambiarReaccionValidation() {
		super();
	}
	
	@Override
	public Result execute(final ReaccionDTO data) {
		var result = Result.create();
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible reaccionar");
		}else {
			result.addMessages(TipoValidation.validate(data.getTipo()).getMessages());
			result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
		}
		return result;
		
	}
}	
