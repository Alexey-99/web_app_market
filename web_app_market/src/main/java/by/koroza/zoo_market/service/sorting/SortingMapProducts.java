package by.koroza.zoo_market.service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.exception.SortingException;

/**
 * The Class SortingMapProducts.
 */
public class SortingMapProducts {

	/** The log. */
	private static Logger log = LogManager.getLogger();

	/** The Constant INSTANCE. */
	private static final SortingMapProducts INSTANCE = new SortingMapProducts();

	/**
	 * Instantiates a new sorting map products.
	 */
	private SortingMapProducts() {
	}

	/**
	 * Gets the single instance of SortingMapProducts.
	 *
	 * @return single instance of SortingMapProducts
	 */
	public static SortingMapProducts getInstance() {
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
	public Map<? extends AbstractProduct, Long> sortProductsMap(Map<? extends AbstractProduct, Long> products,
			Comparator<Entry<? extends AbstractProduct, Long>> comparator) throws SortingException {
		Map<AbstractProduct, Long> sortedMap = new LinkedHashMap<>();
		if (products != null) {
			List<Entry<? extends AbstractProduct, Long>> listEntry = new ArrayList<>();
			products.entrySet().stream().forEach(entry -> listEntry.add(entry));
			listEntry.sort(comparator);
			listEntry.stream().forEach(entry -> sortedMap.put((AbstractProduct) entry.getKey(), entry.getValue()));
		} else {
			log.log(Level.ERROR, "map is null");
			throw new SortingException("map is null");
		}
		return sortedMap;
	}
}