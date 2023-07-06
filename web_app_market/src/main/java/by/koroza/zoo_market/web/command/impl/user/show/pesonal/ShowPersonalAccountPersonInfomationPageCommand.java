package by.koroza.zoo_market.web.command.impl.user.show.pesonal;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowPersonalAccountPersonInfomationPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		if (user != null && user.getRole().getIdRole() >= USER.getIdRole()) {
			router = new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
		} else {
			router = new Router(HOME_PAGE_PATH);
		}
		isRegistratedUser(request);
		return router;
	}
}