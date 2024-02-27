package uco.doo.rugrats.uconnect.api.validator.reaccion;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.EstadoValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.IdentificadorValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.PublicacionValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.AutorValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.FechaReaccionValidation;
import uco.doo.rugrats.uconnect.api.validator.reaccion.common.TipoValidation;
import uco.doo.rugrats.uconnect.dto.ReaccionDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class ReaccionarValidation implements Validation<ReaccionDTO>{
	public static final Result validate(final ReaccionDTO data) {
		return new ReaccionarValidation().execute(data);
	}
	private ReaccionarValidation() {
		super();
	}
	
	@Override
	public Result execute(final ReaccionDTO data) {
		var result = Result.create();
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible reaccionar");
		}else {
			result.addMessages(AutorValidation.validate(data.getAutor()).getMessages());
			result.addMessages(TipoValidation.validate(data.getTipo()).getMessages());
			result.addMessages(EstadoValidation.validate(data.getEstado()).getMessages());
			result.addMessages(FechaReaccionValidation.validate(data.getFechaReaccion()).getMessages());
			result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
			result.addMessages(PublicacionValidation.validate(data.getPublicacion()).getMessages());
		}
		return result;
		
	}

}
