package uco.doo.rugrats.uconnect.api.validator.comentario;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.EstadoValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.IdentificadorValidation;
import uco.doo.rugrats.uconnect.dto.ComentarioDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class CambiarEstadoComentarioValidation implements Validation<ComentarioDTO>{
	public static final Result validate(final ComentarioDTO data) {
		return new CambiarEstadoComentarioValidation().execute(data);
	}
	private CambiarEstadoComentarioValidation() {
		super();
	}
	
	@Override
	public Result execute(final ComentarioDTO data) {
		var result = Result.create();
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible cambiar el estado del comentario");
		}else {
			result.addMessages(EstadoValidation.validate(data.getEstado()).getMessages());
			result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
		}
		return result;
		
	}

}
