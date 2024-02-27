package uco.doo.rugrats.uconnect.api.validator.comentario;

import java.util.UUID;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.IdentificadorValidation;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class EliminarComentarioValidation implements Validation<UUID>{
	public static final Result validate(final UUID data) {
		return new EliminarComentarioValidation().execute(data);
	}
	private EliminarComentarioValidation() {
		super();
	}
	
	@Override
	public Result execute(final UUID data) {
		var result = Result.create();
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible eliminar el comentario");
		}else {
			result.addMessages(IdentificadorValidation.validate(data).getMessages());

		}
		return result;
		
	}

}
