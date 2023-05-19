package by.koroza.zoo_market.web.command.impl;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.PagePathName.VERIFICATION_PERSONAL_ACCOUNT_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.REGISTRATION_FORM_PAGE_PATH;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.UserServiceImpl;
import by.koroza.zoo_market.service.impl.VerificateServiceImpl;
import by.koroza.zoo_market.service.sender.EmailSender;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controler.Router;
import by.koroza.zoo_market.web.generator.GenerationVeriicationCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class VerificationRegistrationInformationCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				user.setRole(UserRole.WAITING_CODE_REGISTRATION);
				insertUserToBDW(user);
				if (user.getId() == 0) {
					user.setId(UserServiceImpl.getInstance().getUserIdByLogin(user.getLogin()));
				}
				session.setAttribute(ATTRIBUTE_USER, user);
				String code = GenerationVeriicationCode.getInstance().getGeneratedCode();
				VerificateServiceImpl.getInstance().addVerificateCodeWithUserId(user.getId(), code);
				EmailSender.getInstance().emailSend("VERIFICATION PERSONAL ACCOUNT",
						"Your code for verification account: " + code, "koroza.alexey99@gmail.com");
			}
			isRegistratedUser(request);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return user != null ? new Router(VERIFICATION_PERSONAL_ACCOUNT_PAGE_PATH)
				: new Router(REGISTRATION_FORM_PAGE_PATH);
	}

	private boolean insertUserToBDW(AbstractRegistratedUser user) throws CommandException {
		try {
			return UserServiceImpl.getInstance().addRegistratedUserToBD(user);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}