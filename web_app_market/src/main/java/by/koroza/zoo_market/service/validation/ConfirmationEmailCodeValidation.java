package by.koroza.zoo_market.service.validation;

import by.koroza.zoo_market.model.entity.code.ConfirmationEmailCode;

public interface ConfirmationEmailCodeValidation {

	public boolean validConfirmationEmailCode(String enteredCode, ConfirmationEmailCode codeDB);
}