package by.koroza.zoo_market.web.command.impl.user.show.form.registration;

import static by.koroza.zoo_market.web.command.name.path.PagePathName.REGISTRATION_FORM_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.controller.router.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowRegistrationUserFormCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		return new Router(REGISTRATION_FORM_PAGE_PATH);
	}
}