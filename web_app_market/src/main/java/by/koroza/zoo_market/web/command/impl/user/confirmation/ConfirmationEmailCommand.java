package by.koroza.zoo_market.web.command.impl.user.confirmation;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.input.InputName.CONFIRMATION_EMAIL_INPUT_CODE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CONFIMARTION_EMAIL_VALIDATED_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.code.ConfirmationEmailCode;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationEmailCodeServiceImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.validation.impl.confirmation.ConfirmationEmailCodeValidationImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ConfirmationEmailCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = null;
		String codeEntered = request.getParameter(CONFIRMATION_EMAIL_INPUT_CODE);
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				ConfirmationEmailCode code = ConfirmationEmailCodeServiceImpl.getInstance()
						.getConfirmationEmailCodeByUserId(user.getId());
				if (code != null && codeEntered != null) {
					if (ConfirmationEmailCodeValidationImpl.getInstance().validConfirmationEmailCode(codeEntered,
							code)) {
						user.setVerificatedEmail(true);
						code.setOpen(ConfirmationEmailCode.getStatusClosed());
						session.setAttribute(ATTRIBUTE_USER, user);
						UserServiceImpl.getInstance().changeConfirmationEmailStatus(user.getId(),
								user.isVerificatedEmail());
						ConfirmationEmailCodeServiceImpl.getInstance().changeConfirmationCodeStatusByUserId(
								user.getId(), code);
						router = new Router(HOME_PAGE_PATH);
					} else {
						router = new Router(CONFIMARTION_EMAIL_VALIDATED_PAGE_PATH);
					}
				} else {
					router = new Router(CONFIMARTION_EMAIL_VALIDATED_PAGE_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}
}