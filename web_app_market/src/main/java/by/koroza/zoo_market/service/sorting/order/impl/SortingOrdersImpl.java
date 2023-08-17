package by.koroza.zoo_market.service.sorting.order.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.service.exception.SortingException;
import by.koroza.zoo_market.service.sorting.order.SortingOrders;
import by.koroza.zoo_market.service.sorting.order.impl.comparator.SortComparatorOrders;

public class SortingOrdersImpl implements SortingOrders {

	private static Logger log = LogManager.getLogger();

	private static final SortingOrders INSTANCE = new SortingOrdersImpl();

	private SortingOrdersImpl() {
	}

	public static SortingOrders getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Order> sortOrders(List<Order> list, SortComparatorOrders comparator) throws SortingException {
		List<Order> listSorting = new LinkedList<>();
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