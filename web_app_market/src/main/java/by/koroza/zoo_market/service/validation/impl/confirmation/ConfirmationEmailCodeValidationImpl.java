package by.koroza.zoo_market.service.validation.impl.confirmation;

import java.time.LocalDateTime;

import by.koroza.zoo_market.model.entity.code.ConfirmationEmailCode;
import by.koroza.zoo_market.service.validation.ConfirmationEmailCodeValidation;

public final class ConfirmationEmailCodeValidationImpl implements ConfirmationEmailCodeValidation {
	private static final ConfirmationEmailCodeValidation INSTANCE = new ConfirmationEmailCodeValidationImpl();

	private ConfirmationEmailCodeValidationImpl() {
	}

	public static ConfirmationEmailCodeValidation getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean validConfirmationEmailCode(String enteredCode, ConfirmationEmailCode codeDB) {
		LocalDateTime localeDateTimeOpenCodeInDB = codeDB.getOpenDateTime();
		LocalDateTime localDateTimeNow = LocalDateTime.now();
		LocalDateTime localeDateTimeClosedCodeInDB = localeDateTimeOpenCodeInDB
				.plus(ConfirmationEmailCode.getDurationValue(), ConfirmationEmailCode.getDurationUnit());
		return (codeDB != null && enteredCode != null) && (codeDB.isOpen())
				&& (localeDateTimeClosedCodeInDB.isAfter(localDateTimeNow)) && (enteredCode.equals(codeDB.getCode()));
	}
}