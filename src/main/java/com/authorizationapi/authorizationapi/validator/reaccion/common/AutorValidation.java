package uco.doo.rugrats.uconnect.api.validator.reaccion.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.api.validator.Validation;
import uco.doo.rugrats.uconnect.dto.ParticipanteGrupoDTO;
import uco.doo.rugrats.uconnect.utils.UtilObject;

public class AutorValidation implements Validation<ParticipanteGrupoDTO> {
	private AutorValidation() {
		super();
	}

	public static final Result validate(final ParticipanteGrupoDTO data) {
		return new AutorValidation().execute(data);
	}

	@Override
	public Result execute(ParticipanteGrupoDTO data) {
		var result = Result.create();

		if (UtilObject.isNull(data)) {
			result.addMessage("No es posible continuar con los datos del participante del grupo vacios");
		} else if (UtilObject.isDefault(data, ParticipanteGrupoDTO.create())) {
			result.addMessage(
					"No es posible continuar con los datos del participante del grupo con sus valores por defecto");
		}
		return result;
	}
}
