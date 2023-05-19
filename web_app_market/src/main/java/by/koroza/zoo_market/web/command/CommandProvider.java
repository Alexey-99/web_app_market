package by.koroza.zoo_market.web.command;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final CommandProvider INSTANCE = new CommandProvider();

	private CommandProvider() {

	}

	public Optional<Command> definaCommand(String commandName) {
		Optional<Command> current = Optional.empty();
		if (commandName != null && !commandName.isBlank()) {
			try {
				CommandType type = CommandType.valueOf(commandName.toUpperCase());
				current = Optional.of(type.getCommand());
			} catch (IllegalArgumentException e) {
				LOGGER.log(Level.ERROR, "Didn't found command with name = '" + commandName + "'. " + e);
				current = Optional.empty(); // to home page
			}
		} else {
			LOGGER.log(Level.WARN, "Command name is = '" + commandName + "'");
			current = Optional.empty(); // to home page
		}
		return current;
	}

	public static CommandProvider getInstance() {
		return INSTANCE;
	}
}