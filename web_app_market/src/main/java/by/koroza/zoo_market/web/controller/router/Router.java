package by.koroza.zoo_market.web.controller.router;

/**
 * The Class Router.
 */
public class Router {
	/**
	 * The Enum RouterType.
	 */
	public enum RouterType {

		/** The forward. */
		FORWARD,
		/** The redirect. */
		REDIRECT
	}

	/** The type of command. */
	private RouterType type;

	/** The page path. */
	private String pagePath;

	/**
	 * Instantiates a new router. Default type of router is FORWARD.
	 *
	 * @param path the path
	 */
	public Router(String path) {
		this.type = RouterType.FORWARD;
		this.pagePath = path;
	}

	/**
	 * Get the type.
	 *
	 * @return the type
	 */
	public RouterType getType() {
		return type;
	}

	/**
	 * Set the forward.
	 */
	public void setForward() {
		this.type = RouterType.FORWARD;
	}

	/**
	 * Set the redirect.
	 */
	public void setRedirect() {
		this.type = RouterType.FORWARD;
	}

	/**
	 * Get the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return pagePath;
	}

	/**
	 * Set the page path.
	 *
	 * @param pagePath the new page path
	 */
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
}