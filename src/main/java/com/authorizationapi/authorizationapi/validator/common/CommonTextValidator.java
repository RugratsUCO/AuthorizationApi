package uco.doo.rugrats.uconnect.api.validator.common;

import uco.doo.rugrats.uconnect.api.validator.Result;
import uco.doo.rugrats.uconnect.utils.UtilText;
import uco.doo.rugrats.uconnect.utils.messages.UconnectApiMessages;

public class CommonTextValidator {
	private CommonTextValidator() {
		super();
	}

	public static final Result execute(final String data, final int minimunLenght, final int maximunLenght) {
		var result = Result.create();

		if (UtilText.isEmpty(data)) {
			result.addMessage(UconnectApiMessages.CommonValidators.CommonTextValidator.TEXT_IS_NULL);

		} else {
			if (!UtilText.getUtilText().textHasLenghtAllowed(data, minimunLenght, maximunLenght)) {
				result.addMessage(UconnectApiMessages.CommonValidators.CommonTextValidator
						.generateMessagetextHasNOTLenghtAllowed(minimunLenght, maximunLenght));
			}
		}
		return result;
	}
}
