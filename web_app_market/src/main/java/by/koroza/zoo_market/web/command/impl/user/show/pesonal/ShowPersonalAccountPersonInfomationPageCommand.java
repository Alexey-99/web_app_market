package by.koroza.zoo_market.web.command.impl.user.show.pesonal;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import static by.koroza.zoo_market.web.command.name.PagePathName.PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH;

import jakarta.servlet.http.HttpServletRequest;

public class ShowPersonalAccountPersonInfomationPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		return new Router(PERSONAL_ACCOUNT_PERSON_INFOMATION_PAGE_PATH);
	}
}
