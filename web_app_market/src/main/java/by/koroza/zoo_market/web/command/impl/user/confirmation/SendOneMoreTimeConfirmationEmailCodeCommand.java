package by.koroza.zoo_market.web.command.impl.user.confirmation;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CONFIMARTION_EMAIL_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.generator.GenerationVeriicationCode;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationServiceImpl;
import by.koroza.zoo_market.service.sender.EmailSender;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SendOneMoreTimeConfirmationEmailCodeCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.getEmail() != null) {
				String code = GenerationVeriicationCode.getInstance().getGeneratedCode();
				ConfirmationServiceImpl.getInstance().addVerificateCodeWithUserId(user.getId(), code);
				EmailSender.getInstance().emailSend("VERIFICATION PERSONAL ACCOUNT",
						"Your code for verification account: " + code, user.getEmail());
				router = new Router(CONFIMARTION_EMAIL_PAGE_PATH);
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		return router;
	}
}