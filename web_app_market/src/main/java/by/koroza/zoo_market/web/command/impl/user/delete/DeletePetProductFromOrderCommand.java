package by.koroza.zoo_market.web.command.impl.user.delete;

import static by.koroza.zoo_market.model.entity.status.UserRole.USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_ORDER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.command.CommandName.COMMAND_SHOW_BACKET_PAGE;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_COMMAND;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PRODUCT_ID;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.servlet.ServletName.MAIN_SERVLET_CONTROLLER_NAME;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.order.OrderServiceImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The Class DeletePetProductFromOrderCommand.
 */
public class DeletePetProductFromOrderCommand implements Command {

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
			if (user != null && user.getRole().getId() >= USER.getId() && user.isVerificatedEmail()) {
				String productIdEntered = request.getParameter(PARAMETER_PRODUCT_ID);
				if (productIdEntered != null && productIdEntered.matches("\\d+")) {
					long productId = Long.parseLong(productIdEntered);
					Pet pet = ProductPetServiceImpl.getInstance().getProductById(productId);
					if (pet != null) {
						Order order = OrderServiceImpl.getInstance().getOpenOrderByUserId(user.getId());
						if (ProductPetServiceImpl.getInstance().transferProductFromOrderToMarket(productId,
								order.getId())) {
							order.setTotalPaymentAmount(order.getTotalPaymentAmount() - pet.getPrice());
							order.setTotalProductsDiscountAmount(order.getTotalProductsDiscountAmount()
									- (pet.getPrice() * pet.getDiscount() / 100));
							order.setTotalPersonDiscountAmount(
									order.getTotalPaymentAmount() * user.getDiscount() / 100);
							order.setTotalDiscountAmount(
									order.getTotalProductsDiscountAmount() + order.getTotalPersonDiscountAmount());
							order.setTotalPaymentWithDiscountAmount(
									order.getTotalPaymentAmount() - order.getTotalDiscountAmount());
							OrderServiceImpl.getInstance().changeOrder(order);
							session.setAttribute(ATTRIBUTE_ORDER, order);
						}
					}
				}
				router = new Router(new StringBuilder().append("/").append(MAIN_SERVLET_CONTROLLER_NAME).append("?")
						.append(PARAMETER_COMMAND).append("=").append(COMMAND_SHOW_BACKET_PAGE).toString());
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