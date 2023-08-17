package by.koroza.zoo_market.service.sorting.order.impl.comparator;

import java.util.Comparator;

import by.koroza.zoo_market.model.entity.market.order.Order;

/**
 * The Interface SortComparatorListProduct.
 */
@FunctionalInterface
public interface SortComparatorOrders extends Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2);
}