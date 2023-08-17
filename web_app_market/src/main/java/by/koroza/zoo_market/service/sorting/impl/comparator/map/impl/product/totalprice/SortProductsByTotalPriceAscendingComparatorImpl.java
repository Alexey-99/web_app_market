package by.koroza.zoo_market.service.sorting.impl.comparator.map.impl.product.totalprice;

import java.util.Map.Entry;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.impl.comparator.map.SortComparatorMap;

/**
 * The Class SortProductsByTotalPriceAscendingComparatorImpl.
 */
public class SortProductsByTotalPriceAscendingComparatorImpl implements SortComparatorMap {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Entry<? extends AbstractProduct, Long> o1, Entry<? extends AbstractProduct, Long> o2) {
		return (int) (o1.getKey().getTotalPrice() - o2.getKey().getTotalPrice());
	}
}