package by.koroza.zoo_market.web.command.impl.admin.change.user.status;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.command.CommandName.COMMAND_ADMIN_PAGE_SHOW_ALL_USERS;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.servlet.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_USER_ID;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class AdminPageChangeUserRoleCommand.
 */
public class AdminPageChangeUserRoleCommand implements Command {

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
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		session.removeAttribute(ATTRIBUTE_ADMIN_PAGE_CHANGE_USER_STATUS_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				String roleId = request.getParameter(ADMIN_PAGE_CHANGE_USER_STATUS_FORM_SELECT_USER_ROLE);
				String userId = request.getParameter(PARAMETER_USER_ID);
				if ((roleId != null && roleId.trim().matches("\\d+")
						&& UserRole.isHaveRoleById(Integer.parseInt(roleId.trim())))
						&& (userId != null && userId.trim().matches("\\d+"))) {
					UserServiceImpl.getInstance().changeRoleStatus(Long.parseLong(userId),
							Integer.parseInt(roleId.trim()));
				}
				router = new Router(new StringBuilder().append("/").append(MAIN_SERVLET_CONTROLLER_NAME).append("?")
						.append(PARAMETER_COMMAND).append("=").append(COMMAND_ADMIN_PAGE_SHOW_ALL_USERS).toString());
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