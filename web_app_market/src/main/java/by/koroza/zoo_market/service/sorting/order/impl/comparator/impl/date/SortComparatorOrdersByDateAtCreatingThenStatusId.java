package by.koroza.zoo_market.service.sorting.order.impl.comparator.impl.date;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.service.sorting.order.impl.comparator.SortComparatorOrders;

/**
 * The Class SortComparatorOrdersByDateAtCreating.
 */
public class SortComparatorOrdersByDateAtCreatingThenStatusId implements SortComparatorOrders {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Order o1, Order o2) {
		int valueCompareDate = o1.getDateCreation().compareTo(o2.getDateCreation());
		return valueCompareDate != 0 ? valueCompareDate : o2.getStatus().getId() - o1.getStatus().getId();
	}
}