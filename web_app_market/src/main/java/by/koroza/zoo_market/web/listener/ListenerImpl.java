package by.koroza.zoo_market.web.listener;

import static by.koroza.zoo_market.web.command.name.AttributeName.ATTRIBUTE_SESSION_LOCALE;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ListenerImpl
 *
 */
@WebListener
public class ListenerImpl implements HttpSessionListener {

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
		se.getSession().setAttribute(ATTRIBUTE_SESSION_LOCALE, "ru_RU");
		String sessionId = se.getSession().getId();
		System.out.println(
				"--------------------------------- session created -------------------------------------------------------------");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println(
				"--------------------------------- session destroyed -------------------------------------------------------------");
	}
}