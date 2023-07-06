package by.koroza.zoo_market.web.command.impl.user.show.confirmation;

import static by.koroza.zoo_market.web.command.name.path.PagePathName.CONFIMARTION_EMAIL_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;

public class ShowConfirmationEmailFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		isRegistratedUser(request);
		return new Router(CONFIMARTION_EMAIL_PAGE_PATH);
	}
}