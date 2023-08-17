package by.koroza.zoo_market.service.sorting.impl.comparator.map.impl.product.other.description;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.impl.comparator.map.SortComparatorMap;

/**
 * The Class SortProductsByProductDescriptionAscendingComparatorImpl.
 */
public class SortProductsByProductDescriptionAscendingComparatorImpl implements SortComparatorMap {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		String productDescriptionFirst = o1.getKey().getClass().equals(Pet.class) ? EMPTY_LINE_NAME
				: ((FeedAndOther) o1.getKey()).getDescriptions();
		String productDescriptionSecond = o2.getKey().getClass().equals(Pet.class) ? EMPTY_LINE_NAME
				: ((FeedAndOther) o2.getKey()).getDescriptions();
		return productDescriptionFirst.compareTo(productDescriptionSecond);
	}
}