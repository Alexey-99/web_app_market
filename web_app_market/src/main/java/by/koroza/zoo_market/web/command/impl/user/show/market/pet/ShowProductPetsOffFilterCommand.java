package by.koroza.zoo_market.web.command.impl.user.show.market.pet;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_SESSION_LOCALE;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.REQUEST_ATTRIBUTE_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.parameter.ParameterName.PARAMETER_NUMBER_PAGE;
import static by.koroza.zoo_market.web.command.name.path.PagePathName.PRODUCTS_PETS_PAGE_PATH;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.exception.ServiceException;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.factory.impl.MarketFilterProductFactoryImpl;
import by.koroza.zoo_market.service.impl.product.ProductPetServiceImpl;
import by.koroza.zoo_market.service.sorting.impl.SortingProductsImpl;
import by.koroza.zoo_market.service.sorting.impl.comparator.list.product.impl.id.SortProductsByIdAscendingComparatorImpl;
import by.koroza.zoo_market.web.command.Command;
import by.koroza.zoo_market.web.command.exception.CommandException;
import by.koroza.zoo_market.web.controller.router.Router;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ShowProductPetsOffFilterCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_INPUT_EXCEPTION_TYPE_AND_MASSAGE);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP);
		session.removeAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER);
		try {
			Map<Pet, Long> pets = ProductPetServiceImpl.getInstance().getAllProductsPetsAndNumberOfUnits();
			List<Entry<Pet, Long>> sortedPets = SortingProductsImpl.getInstance()
					.sortProductsPets(pets.entrySet().stream().toList(), new SortProductsByIdAscendingComparatorImpl());
			session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, sortedPets);
			Map<String, Set<String>> filterMap = MarketFilterProductFactoryImpl.getInstance()
					.createFilterPets(pets.keySet(), (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE));
			session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}
}