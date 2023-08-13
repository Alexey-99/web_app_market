package by.koroza.zoo_market.web.command;

import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The Interface Command.
 */
@FunctionalInterface
public interface Command {

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	public Router execute(HttpServletRequest request) throws CommandException;
}