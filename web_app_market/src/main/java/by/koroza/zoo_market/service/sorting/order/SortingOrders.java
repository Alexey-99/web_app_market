package by.koroza.zoo_market.service.sorting.order;

import java.util.List;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.sorting.order.impl.comparator.SortComparatorOrders;

public interface SortingOrders {

	public List<Order> sortOrders(List<Order> list, SortComparatorOrders comparator) throws SortingException;
}