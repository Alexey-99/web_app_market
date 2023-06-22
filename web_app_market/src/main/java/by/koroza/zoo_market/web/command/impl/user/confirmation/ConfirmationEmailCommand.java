package by.koroza.zoo_market.web.command.impl.user.confirmation;

import static by.koroza.zoo_market.web.command.name.InputName.VERIFICATION_PERSON_ACCOUNT_INPUT_CODE;

import static by.koroza.zoo_market.web.command.name.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.VERIFICATION_PERSONAL_ACCOUNT_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.REGISTRATION_FORM_PAGE_PATH;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;

import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.UserServiceImpl;
import by.koroza.zoo_market.service.impl.VerificateServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ConfirmationEmailCommand implements Command {
	@SuppressWarnings("unused")
	private static final boolean VERIFICATE_CODE_STATUS_OPEN = true;
	private static final boolean VERIFICATE_CODE_STATUS_CLOSED = false;

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = null;
		String codeInput = request.getParameter(VERIFICATION_PERSON_ACCOUNT_INPUT_CODE);
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				if (user.getId() == 0) {
					user.setId(UserServiceImpl.getInstance().getUserIdByLogin(user.getLogin()));
				}
				String code = VerificateServiceImpl.getInstance().getVerificateCodeByUserId(user.getId());
				if (codeInput.equals(code)) {
					user.setVerificatedEmail(true);
					session.setAttribute(ATTRIBUTE_USER, user);
					UserServiceImpl.getInstance().changeVerificationEmailStatus(user.getId(), true);
					VerificateServiceImpl.getInstance().changeVerificateCodeStatusByUserId(user.getId(), code,
							VERIFICATE_CODE_STATUS_CLOSED);
					router = new Router(HOME_PAGE_PATH);
				} else {
					router = new Router(VERIFICATION_PERSONAL_ACCOUNT_VALIDATED_PAGE_PATH);
				}
			} else {
				router = new Router(REGISTRATION_FORM_PAGE_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}

}
