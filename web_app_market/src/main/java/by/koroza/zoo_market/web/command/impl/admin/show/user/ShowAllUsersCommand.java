package by.koroza.zoo_market.web.command.impl.admin.show.user;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_USERS;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_USERS_PAGE_PATH;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.impl.user.UserServiceImpl;
import by.koroza.zoo_market.service.sorting.user.impl.SortingUsersImpl;
import by.koroza.zoo_market.service.sorting.user.impl.comparator.impl.SortComparatorUsersById;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowAllUsersCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				List<User> users = SortingUsersImpl.getInstance().sortUsers(UserServiceImpl.getInstance().getAllUsers(),
						new SortComparatorUsersById());
				session.setAttribute(ATTRIBUTE_LIST_USERS, users);
				router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_USERS_PAGE_PATH);
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}
}