package by.koroza.zoo_market.web.listener;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.language.LanguageName.RUSSIAN;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ListenerImpl
 *
 */
@WebListener
public class ListenerImpl implements HttpSessionListener {
	private static Logger log = LogManager.getLogger();

	/**
	 * Default constructor.
	 */
	public ListenerImpl() {
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute(ATTRIBUTE_SESSION_LOCALE, RUSSIAN);
		log.log(Level.INFO, "session created!");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		log.log(Level.INFO, "session destroyed!");
	}
}