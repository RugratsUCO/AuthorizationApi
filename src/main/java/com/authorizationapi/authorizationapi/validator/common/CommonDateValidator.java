package uco.doo.rugrats.uconnect.api.validator.common;

import java.time.LocalDateTime;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.utils.UtilDate;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

public class CommonDateValidator {
	private CommonDateValidator() {
		super();
	}

	public static final Result execute(final LocalDateTime data) {
		var result = Result.create();

		if (UtilDate.isNull(data)) {
			result.addMessage(UconnectApiMessages.CommonValidators.CommonDateValidator.DATE_IS_NULL);
		}
		return result;
	}
}
