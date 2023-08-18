package by.koroza.zoo_market.web.command.impl.admin.show.products.information;

import static by.koroza.zoo_market.model.entity.status.UserRole.ADMIN;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_USER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.SESSION_ATTRIBUTE_SHOW_MORE_DETAILS_ABOUT_RODUCT;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_PRODUCT_ID;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.HOME_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET_PAGE_PATH;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_ALL_PRODUCTS_PATH;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.user.User;
import by.koroza.zoo_market.service.ProductPetService;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowMoreDetailsAboutProductPetCommand extends ShowMoreDetailsAboutProduct implements Command {
	private static Logger log = LogManager.getLogger();

	private final ProductPetService PRODUCT_PET_SERVICE = ProductPetServiceImpl.getInstance();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		Map<String, Object> information = new LinkedHashMap<>();
		try {
			if (user != null && user.isVerificatedEmail() && user.getRole().getId() == ADMIN.getId()) {
				String productId = request.getParameter(PARAMETER_PRODUCT_ID);
				if (productId != null && productId.matches("\\d+")) {
					long petIdLong = Long.parseLong(productId);
					Pet pet = PRODUCT_PET_SERVICE.getProductPetById(petIdLong);
					if (pet != null) {
						information.put(PRODUCT, pet);
						information.put(QUANTITY_AVAILABLE,
								PRODUCT_PET_SERVICE.getFreeNumberOfUnitsByProductId(petIdLong));
						information.put(QUANTITY_IN_RESERVE,
								PRODUCT_PET_SERVICE.getQuantityInOpenOrdersByProductId(petIdLong));
						session.setAttribute(SESSION_ATTRIBUTE_SHOW_MORE_DETAILS_ABOUT_RODUCT, information);
						router = new Router(PERSONAL_ACCOUNT_ADMIN_PAGE_SHOW_MORE_DETAILS_ABOUT_PRODUCT_PET_PAGE_PATH);
					}
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