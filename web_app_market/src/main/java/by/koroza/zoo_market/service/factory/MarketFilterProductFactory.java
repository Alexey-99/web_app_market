package by.koroza.zoo_market.service.factory;

import java.util.Map;
import java.util.Set;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;

/**
 * A factory for creating MarketFilterProduct objects.
 */
public interface MarketFilterProductFactory {

	/**
	 * Create a new MarketFilterProduct object.
	 *
	 * @param products      the products
	 * @param sessionLocale the session locale
	 * @return the map< string, set< string>>
	 */
	public Map<String, Set<String>> createFilterFeedAndOther(Set<FeedAndOther> products, String sessionLocale);

	/**
	 * Create a new MarketFilterProduct object.
	 *
	 * @param petsList      the pets set
	 * @param sessionLocale the session locale
	 * @return the map< string, set< string>>
	 */
	public Map<String, Set<String>> createFilterPets(Set<Pet> petsList, String sessionLocale);
}