package by.koroza.zoo_market.web.command.impl.user.registration.verification;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.CONFIMARTION_EMAIL_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.HashGeneratorException;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.confirmation.ConfirmationEmailCodeServiceImpl;
import by.koroza.zoo_market.service.impl.hash.HashGeneratorImpl;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class VerificationRegistrationInformationCommand.
 */
public class VerificationRegistrationInformationCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null) {
				user.setRole(USER);
				user.setVerificatedEmail(false);
				insertUserToBD(user);
				session.setAttribute(ATTRIBUTE_USER, user);
				ConfirmationEmailCodeServiceImpl.getInstance().sendConfirmationEmailCode(user.getId(), user.getEmail());
			}
		} catch (ServiceException | HashGeneratorException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return user != null ? new Router(CONFIMARTION_EMAIL_PAGE_PATH) : new Router(HOME_PAGE_PATH);
	}

	/**
	 * Insert user to BD.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException       the service exception
	 * @throws HashGeneratorException the hash generator exception
	 */
	private boolean insertUserToBD(User user) throws ServiceException, HashGeneratorException {
		user.setPassword(HashGeneratorImpl.getInstance().getHash(user.getPassword()));
		return UserServiceImpl.getInstance().addUser(user);
	}
}