package by.koroza.zoo_market.web.command.impl.user.sign;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignOutPersonalAccountCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_USER);
		isRegistratedUser(request);
		return new Router(HOME_PAGE_PATH);
	}
}