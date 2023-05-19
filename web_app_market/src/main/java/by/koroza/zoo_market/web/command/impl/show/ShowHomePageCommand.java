package by.koroza.zoo_market.web.command.impl.show;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.name.PagePathName;
import by.koroza.zoo_market.web.controler.Router;

import jakarta.servlet.http.HttpServletRequest;

public class ShowHomePageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		isRegistratedUser(request);
		return new Router(PagePathName.HOME_PAGE_PATH);
	}

}
