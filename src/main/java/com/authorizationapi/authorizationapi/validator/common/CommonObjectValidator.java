package uco.doo.rugrats.uconnect.api.validator.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.utils.UtilObject;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

public interface CommonObjectValidator {

	static <O> Result excecute(final O data, O defaultObject, final String businessName) {
		var result = Result.create();

		if (UtilObject.isNull(data)) {
			result.addMessage(UconnectApiMessages.CommonValidators.CommonObjectValidator
					.generateMessageObjectIsNull(businessName));
		} else if (UtilObject.isDefault(data, defaultObject)) {
			result.addMessage(UconnectApiMessages.CommonValidators.CommonObjectValidator
					.generateMessageObjectIsDefault(businessName));
		}
		return result;
	}
}
