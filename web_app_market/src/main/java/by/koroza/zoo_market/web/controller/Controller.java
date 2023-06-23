package by.koroza.zoo_market.web.controller;

import static by.koroza.zoo_market.web.command.name.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.CommandProvider;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.exception.ControllerException;

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
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @throws CommandException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		String commandName = request.getParameter(PARAMETER_COMMAND).trim();
		Optional<Command> optionalCommand = CommandProvider.getInstance().definaCommand(commandName);
		Command command = (optionalCommand.isPresent() ? optionalCommand.get() : null);
		try {
			if (command == null) {
				throw new CommandException("commnd is null");
			}
			Router router = command.execute(request);
			switch (router.getType()) {
			case REDIRECT -> response.sendRedirect(router.getPagePath());
			case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
			default -> {
				LOGGER.log(Level.ERROR, "unknown router type: {}", router.getType());
				// TODO response.sendRedirect(ERROR_404_PAGE);
			}
			}
		} catch (IOException | ServletException | CommandException e) {
			throw new ControllerException(e);
		}
	}
}