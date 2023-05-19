package by.koroza.zoo_market.dao.factory;

import java.util.List;
import java.util.Map;

import by.koroza.zoo_market.model.entity.market.abstraction.AbstractProduct;
import by.koroza.zoo_market.model.entity.market.order.Order;

public class OrderFactory {
	private static final OrderFactory INSTANCE = new OrderFactory();

	private OrderFactory() {
	}

	public static OrderFactory getInstance() {
		return INSTANCE;
	}

	public void connectOrdersAndProducts(List<Order> orders,
			Map<Long, ? extends AbstractProduct> mapOrderIdAndProductsPet,
			Map<Long, ? extends AbstractProduct> mapOrderIdAndProductsFeedsAndOther) {

	}

}
