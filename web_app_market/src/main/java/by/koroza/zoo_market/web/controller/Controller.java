package by.koroza.zoo_market.web.controller;

import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.ERROR_PAGE_404_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.ERROR_PAGE_500_PATH;
import static by.koroza.zoo_market.web.command.name.servlet.ServletName.MAIN_SERVLET_CONTROLLER_NAME;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SERVLET_COMMAND_EXCEPTION;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.CommandProvider;
import by.koroza.zoo_market.web.command.exception.CommandException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/" + MAIN_SERVLET_CONTROLLER_NAME)
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LogManager.getLogger();

	/**
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		processRequest(request, response);
	}

	/**
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		processRequest(request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @throws CommandException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String commandName = request.getParameter(PARAMETER_COMMAND).trim();
		Optional<Command> optionalCommand = CommandProvider.getInstance().definaCommand(commandName);
		Command command = (optionalCommand.isPresent() ? optionalCommand.get() : null);
		Router router = null;
		try {
			router = command != null ? command.execute(request) : new Router(ERROR_PAGE_404_PATH);
		} catch (CommandException e) {
			request.getSession().setAttribute(ATTRIBUTE_SERVLET_COMMAND_EXCEPTION, e);
			router = new Router(ERROR_PAGE_500_PATH);
		} finally {
			if (router == null) {
				router = new Router(ERROR_PAGE_500_PATH);
			}
		}
		switch (router.getType()) {
		case REDIRECT -> response.sendRedirect(router.getPagePath());
		case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
		default -> {
			log.log(Level.ERROR, "unknown router type: {}", router.getType());
			response.sendRedirect(ERROR_PAGE_500_PATH);
			request.getRequestDispatcher(ERROR_PAGE_500_PATH).forward(request, response);
		}
		}
	}
}