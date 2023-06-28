package by.koroza.zoo_market.web.command.impl.user.verification;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_USER;

import static by.koroza.zoo_market.web.command.name.PagePathName.REGISTRATION_FORM_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.PagePathName.CONFIMARTION_EMAIL_PAGE_PATH;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.generator.GenerationVeriicationCode;
import by.koroza.zoo_market.service.hash.HashGenerator;
import by.koroza.zoo_market.service.impl.VerificateServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.sender.EmailSender;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class VerificationRegistrationInformationCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		AbstractRegistratedUser user = (AbstractRegistratedUser) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				user.setRole(UserRole.USER);
				insertUserToBD(user);
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
		return user != null ? new Router(CONFIMARTION_EMAIL_PAGE_PATH) : new Router(REGISTRATION_FORM_PAGE_PATH);
	}

	private boolean insertUserToBD(AbstractRegistratedUser user) throws CommandException {
		try {
			user.setPassword(HashGenerator.getInstance().getHash(user.getPassword()));
			return UserServiceImpl.getInstance().addRegistratedUserToBD(user);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
	}
}