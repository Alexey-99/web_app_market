package by.koroza.zoo_market.service.sorting.comparator.map.impl.product.price;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.map.SortComparatorMap;

/**
 * The Class SortProductsByPriceDescendingComparatorImpl.
 */
public class SortProductsByPriceDescendingComparatorImpl implements SortComparatorMap {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		return (int) (o2.getKey().getPrice() - o1.getKey().getPrice());
	}
}