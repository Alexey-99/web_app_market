package by.koroza.zoo_market.web.command.impl.admin.change.order;

import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PRODUCT_ID;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;
import static by.koroza.zoo_market.web.command.name.servlet.ServletName.MAIN_SERVLET_CONTROLLER_NAME;
import static by.koroza.zoo_market.web.command.name.command.CommandName.COMMAND_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_ORDER_ID;
import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.input.InputName.ADMIN_PAGE_CHANGE_QUANTITY_PRODUCT_IN_OPEN_ORDER_FORM_INPUT;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.status.OrderStatus;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class ChangeQuantityProductPetCommand.
 */
public class ChangeQuantityProductPetCommand implements Command {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				String productId = request.getParameter(PARAMETER_PRODUCT_ID);
				if (productId != null && productId.matches("^(\\d)+$")) {
					productId = productId.trim();
					String orderId = request.getParameter(PARAMETER_ORDER_ID);
					String quantity = request.getParameter(ADMIN_PAGE_CHANGE_QUANTITY_PRODUCT_IN_OPEN_ORDER_FORM_INPUT);
					if ((orderId != null && orderId.matches("^(\\d)+$"))
							&& (quantity != null && quantity.matches("^(\\d)+$"))) {
						OrderServiceImpl.getInstance().changeQuantityProductPetInOrderByOrderId(
								Long.parseLong(orderId.trim()), OrderStatus.OPEN.getId(), Long.parseLong(productId),
								Long.parseLong(quantity.trim()), user.getDiscount());
					}
					router = new Router(new StringBuilder().append("/").append(MAIN_SERVLET_CONTROLLER_NAME).append("?")
							.append(PARAMETER_COMMAND).append("=")
							.append(COMMAND_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET).append("&")
							.append(PARAMETER_PRODUCT_ID).append("=").append(productId).toString());
				} else {
					router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH);
				}
			} else {
				router = new Router(HOME_PAGE_PATH);
			}
		} catch (ServiceException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		return router;
	}
}