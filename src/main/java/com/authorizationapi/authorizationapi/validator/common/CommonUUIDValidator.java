package uco.doo.rugrats.uconnect.api.validator.common;

import java.util.UUID;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.utils.UtilUUID;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

public class CommonUUIDValidator {

	private CommonUUIDValidator() {
		super();
	}

	public static final Result execute(final UUID data) {
		var result = Result.create();

		if (UtilUUID.isNull(data)) {
			result.addMessage(UconnectApiMessages.CommonValidators.CommonUUIDValidator.UUID_IS_NULL);

		}
		return result;
	}

}
