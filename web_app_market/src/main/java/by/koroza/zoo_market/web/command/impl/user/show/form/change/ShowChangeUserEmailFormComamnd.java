package by.koroza.zoo_market.web.command.impl.user.show.form.change;

import static by.koroza.zoo_market.web.command.name.path.PagePathName.CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowChangeUserEmailFormComamnd implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		return new Router(CHANGE_EMAIL_FORM_VALIDATED_PAGE_PATH);
	}
}