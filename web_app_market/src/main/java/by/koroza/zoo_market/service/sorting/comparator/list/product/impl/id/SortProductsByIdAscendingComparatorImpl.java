package by.koroza.zoo_market.service.sorting.comparator.list.product.impl.id;

import by.koroza.zoo_market.model.entity.market.product.abstraction.AbstractProduct;
import by.koroza.zoo_market.service.sorting.comparator.list.product.SortComparatorListProduct;

/**
 * The Class SortProductsByIdAscendingComparatorImpl.
 */
public class SortProductsByIdAscendingComparatorImpl implements SortComparatorListProduct {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(AbstractProduct o1, AbstractProduct o2) {
		return (int) (o1.getId() - o2.getId());
	}
}