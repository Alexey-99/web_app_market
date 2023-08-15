package by.koroza.zoo_market.service.sorting;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.sorting.comparator.list.product.SortComparatorListProduct;
import by.koroza.zoo_market.service.sorting.comparator.map.SortComparatorMap;

/**
 * The Class SortingProducts.
 */
public class SortingProducts {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final SortingProducts INSTANCE = new SortingProducts();

	/**
	 * Instantiates a new sorting map products.
	 */
	private SortingProducts() {
	}

	/**
	 * Get the single instance of SortingMapProducts.
	 *
	 * @return single instance of SortingMapProducts
	 */
	public static SortingProducts getInstance() {
		return INSTANCE;
	}

	public Map<? extends AbstractProduct, Long> sortProductsMap(Map<? extends AbstractProduct, Long> products,
			SortComparatorMap comparator) throws SortingException {
		Map<AbstractProduct, Long> sortedMap = new LinkedHashMap<>();
		if (products != null) {
			if (comparator != null) {
				List<Entry<? extends AbstractProduct, Long>> listEntry = new ArrayList<>();
				products.entrySet().stream().forEach(entry -> listEntry.add(entry));
				listEntry.sort(comparator);
				listEntry.stream().forEach(entry -> sortedMap.put((AbstractProduct) entry.getKey(), entry.getValue()));
			} else {
				log.log(Level.ERROR, "comparator is null");
				throw new SortingException("comparator is null");
			}
		} else {
			log.log(Level.ERROR, "map is null");
			throw new SortingException("map is null");
		}
		return sortedMap;
	}

	/**
	 * Sort products map.
	 *
	 * @param list       the products
	 * @param comparator the comparator
	 * @return the list<? extends abstract product, long>
	 * @throws SortingException the sorting exception
	 */
	public List<Entry<Pet, Long>> sortProductsPets(List<Entry<Pet, Long>> list, SortComparatorListProduct comparator)
			throws SortingException {
		if (list != null) {
			if (comparator != null) {
				list.sort(comparator);
			} else {
				log.log(Level.ERROR, "comparator is null");
				throw new SortingException("comparator is null");
			}
		} else {
			log.log(Level.ERROR, "list is null");
			throw new SortingException("list is null");
		}
		return new LinkedList<>(list);
	}

	/**
	 * Sort products map.
	 *
	 * @param list       the products
	 * @param comparator the comparator
	 * @return the list<? extends abstract product, long>
	 * @throws SortingException the sorting exception
	 */
	public List<Entry<FeedAndOther, Long>> sortProductsFeedsAndOther(List<Entry<FeedAndOther, Long>> list,
			SortComparatorListProduct comparator) throws SortingException {
		if (list != null) {
			if (comparator != null) {
				list.sort(comparator);
			} else {
				log.log(Level.ERROR, "comparator is null");
				throw new SortingException("comparator is null");
			}
		} else {
			log.log(Level.ERROR, "list is null");
			throw new SortingException("list is null");
		}
		return new LinkedList<>(list);
	}
}