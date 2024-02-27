package uco.doo.rugrats.uconnect.api.validator.comentario;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.ComentarioPadreValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.ContenidoValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.EstadoValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.FechaPublicacionValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.IdentificadorValidation;
import uco.doo.rugrats.uconnect.api.validator.comentario.common.PublicacionValidation;
import uco.doo.rugrats.uconnect.dto.ComentarioDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class ComentarComentarioValidation implements Validation<ComentarioDTO>{
	public static final Result validate(final ComentarioDTO data) {
		return new ComentarComentarioValidation().execute(data);
	}
	private ComentarComentarioValidation() {
		super();
	}
	
	@Override
	public Result execute(final ComentarioDTO data) {
		var result = Result.create();
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible registrar un nuevo comentario");
		}else {
			result.addMessages(ComentarioPadreValidation.validate(data.getComentarioPadre()).getMessages());
			result.addMessages(ContenidoValidation.validate(data.getContenido()).getMessages());
			result.addMessages(EstadoValidation.validate(data.getEstado()).getMessages());
			result.addMessages(FechaPublicacionValidation.validate(data.getFechaPublicacion()).getMessages());
			result.addMessages(IdentificadorValidation.validate(data.getIdentificador()).getMessages());
			result.addMessages(PublicacionValidation.validate(data.getPublicacion()).getMessages());
		}
		return result;
		
	}

}
