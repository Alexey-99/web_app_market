package by.koroza.zoo_market.web.command.impl.user.show.market.pet.page;

import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_LIST_PRODUCTS_PETS;
import static by.koroza.zoo_market.web.command.name.attribute.AttributeName.ATTRIBUTE_PRODUCTS_PETS_FILTER;
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

import by.koroza.zoo_market.model.entity.filter.FilterPet;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.ProductPetService;
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

public class ShowProductPetNumberPageCommand implements Command {
	private static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		final SortingProductsImpl SORT_PRODUCTS = SortingProductsImpl.getInstance();
		final ProductPetService PRODUCT_PET_SERVICE = ProductPetServiceImpl.getInstance();
		HttpSession session = request.getSession();
		try {
			Map<Pet, Long> allProductsPets = PRODUCT_PET_SERVICE.getAllProductsPetsAndNumberOfUnits();
			Map<String, Set<String>> filterMap = MarketFilterProductFactoryImpl.getInstance().createFilterPets(
					allProductsPets.keySet(), (String) session.getAttribute(ATTRIBUTE_SESSION_LOCALE));
			session.setAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER_MAP, filterMap);
			if (session.getAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER) != null) {
				FilterPet filterPet = (FilterPet) session.getAttribute(ATTRIBUTE_PRODUCTS_PETS_FILTER);
				List<Entry<Pet, Long>> productsPetsByFilter = PRODUCT_PET_SERVICE.getProductsPetsByFilter(filterPet);
				productsPetsByFilter = SORT_PRODUCTS.sortProductsPets(productsPetsByFilter,
						new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, productsPetsByFilter);
			} else {
				List<Entry<Pet, Long>> products = SORT_PRODUCTS.sortProductsPets(
						allProductsPets.entrySet().stream().toList(), new SortProductsByIdAscendingComparatorImpl());
				session.setAttribute(ATTRIBUTE_LIST_PRODUCTS_PETS, products);
			}
		} catch (ServiceException | SortingException e) {
			log.log(Level.ERROR, e.getMessage());
			throw new CommandException(e);
		}
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_PAGE, request.getParameter(PARAMETER_NUMBER_PAGE));
		return new Router(PRODUCTS_PETS_PAGE_PATH);
	}
}