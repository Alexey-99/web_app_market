package by.koroza.zoo_market.web.command.impl.user.show.market.pet.page;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_PETS_PAGE_PATH;

import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.factory.impl.MarketFilterProductFactoryImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductPetNumberPageCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		try {
			List<Pet> allProductsPets = ProductPetServiceImpl.getInstance().getAllHavingProductsPets();
			Map<String, Set<String>> filterMap = MarketFilterProductFactoryImpl.getInstance()
					.createFilterPets(allProductsPets, (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE));
			session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		isRegistratedUser(request);
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}
}