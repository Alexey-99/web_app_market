package by.koroza.zoo_market.service.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;

public interface MarketFilterProductFactory {

	/**
	 * Creates a new MarketFilterProduct object.
	 *
	 * @param products      the products
	 * @param sessionLocale the session locale
	 * @return the map< string, set< string>>
	 */
	public Map<String, Set<String>> createFilterFeedAndOther(List<FeedAndOther> products, String sessionLocale);

	/**
	 * Creates a new MarketFilterProduct object.
	 *
	 * @param petsList      the pets list
	 * @param sessionLocale the session locale
	 * @return the map< string, set< string>>
	 */
	public Map<String, Set<String>> createFilterPets(List<Pet> petsList, String sessionLocale);
}