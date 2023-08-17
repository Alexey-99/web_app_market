package by.koroza.zoo_market.service.sorting.product.impl;

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
import by.koroza.zoo_market.service.sorting.product.SortingProducts;
import by.koroza.zoo_market.service.sorting.product.impl.comparator.list.product.SortComparatorListProduct;
import by.koroza.zoo_market.service.sorting.product.impl.comparator.map.SortComparatorMap;

/**
 * The Class SortingProductsImpl.
 */
public class SortingProductsImpl implements SortingProducts {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final SortingProducts INSTANCE = new SortingProductsImpl();

	/**
	 * Instantiates a new sorting map products.
	 */
	private SortingProductsImpl() {
	}

	/**
	 * Get the single instance of SortingMapProducts.
	 *
	 * @return single instance of SortingMapProducts
	 */
	public static SortingProducts getInstance() {
		return INSTANCE;
	}

	/**
	 * Sort products map.
	 *
	 * @param products   the products
	 * @param comparator the comparator
	 * @return the map<? extends abstract product, long>
	 * @throws SortingException the sorting exception
	 */
	@Override
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
	 * Sort products pets.
	 *
	 * @param list       the list
	 * @param comparator the comparator
	 * @return the list
	 * @throws SortingException the sorting exception
	 */
	@Override
	public List<Entry<Pet, Long>> sortProductsPets(List<Entry<Pet, Long>> list, SortComparatorListProduct comparator)
			throws SortingException {
		List<Entry<Pet, Long>> listSorting = new LinkedList<>();
		if (list != null) {
			listSorting.addAll(list);
			if (comparator != null) {
				listSorting.sort(comparator);
			} else {
				log.log(Level.ERROR, "comparator is null");
				throw new SortingException("comparator is null");
			}
		} else {
			log.log(Level.ERROR, "list is null");
			throw new SortingException("list is null");
		}
		return listSorting;
	}

	/**
	 * Sort products feeds and other.
	 *
	 * @param list       the list
	 * @param comparator the comparator
	 * @return the list
	 * @throws SortingException the sorting exception
	 */
	@Override
	public List<Entry<FeedAndOther, Long>> sortProductsFeedsAndOther(List<Entry<FeedAndOther, Long>> list,
			SortComparatorListProduct comparator) throws SortingException {
		List<Entry<FeedAndOther, Long>> listSorting = new LinkedList<>();
		if (list != null) {
			listSorting.addAll(list);
			if (comparator != null) {
				listSorting.sort(comparator);
			} else {
				log.log(Level.ERROR, "comparator is null");
				throw new SortingException("comparator is null");
			}
		} else {
			log.log(Level.ERROR, "list is null");
			throw new SortingException("list is null");
		}
		return listSorting;
	}
}