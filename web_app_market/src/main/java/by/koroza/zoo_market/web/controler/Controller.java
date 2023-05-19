package by.koroza.zoo_market.web.controler;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.CommandProvider;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.command.exception.RuntimeCommandException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (IOException | ServletException | CommandException e) {
			throw new RuntimeCommandException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			processRequest(request, response);
		} catch (IOException | ServletException | CommandException e) {
			throw new RuntimeCommandException(e);
		}
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @throws CommandException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, CommandException {
		String commandName = request.getParameter("command");
		Optional<Command> optionalCommand = CommandProvider.getInstance().definaCommand(commandName);
		Command command = (optionalCommand.isPresent() ? optionalCommand.get() : null); // TODO
		Router router = command.execute(request);
		switch (router.getType()) {
		case REDIRECT -> response.sendRedirect(router.getPagePath());
		case FORWARD -> request.getRequestDispatcher(router.getPagePath()).forward(request, response);
		default -> {
			LOGGER.log(Level.ERROR, "unknown router type: {}", router.getType());
			// TODO response.sendRedirect(ERROR_404_PAGE);
		}
		}
	}
}