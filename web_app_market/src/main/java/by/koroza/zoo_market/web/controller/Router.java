package by.koroza.zoo_market.web.controller;

public class Router {
	/**
	 * The Enum RouterType.
	 */
	public enum RouterType {
		FORWARD, REDIRECT
	}

	/** The type of command. */
	private RouterType type;

	/** The page path. */
	private String pagePath;

	/**
	 * Instantiates a new router. Default type of router is FORWARD.
	 *
	 * @param pagePath the page path
	 */
	public Router(String path) {
		this.type = RouterType.FORWARD;
		this.pagePath = path;
	}

	public RouterType getType() {
		return type;
	}

	public void setType(RouterType type) {
		this.type = type;
	}

	public void setForward() {
		this.type = RouterType.FORWARD;
	}

	public void setRedirect() {
		this.type = RouterType.FORWARD;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}
}