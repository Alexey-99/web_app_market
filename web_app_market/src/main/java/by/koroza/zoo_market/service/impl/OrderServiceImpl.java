package by.koroza.zoo_market.service.impl;

import java.util.List;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.impl.OrderDaoImpl;
import by.koroza.zoo_market.model.calculation.Culculator;
import by.koroza.zoo_market.model.entity.market.abstraction.AbstractProduct;
import by.koroza.zoo_market.model.entity.market.order.Order;
import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;
import by.koroza.zoo_market.model.entity.market.product.Pet;
import by.koroza.zoo_market.service.OrderService;
import by.koroza.zoo_market.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {
	private static final OrderService INSTANCE = new OrderServiceImpl();

	private OrderServiceImpl() {
	}

	public static OrderService getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Order> getOrderProductsByUserId(long userId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().getOrderProductsByUserId(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addOrder(Order order, long userId) throws ServiceException {
		try {
			return OrderDaoImpl.getInstance().addOrder(order, userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public double calcTotalPaymentAmount(List<Pet> pets, List<FeedAndOther> otherProducts) {
		return calcTotalPaymentAmountTypeProduts(pets) + calcTotalPaymentAmountTypeProduts(otherProducts);
	}

	@Override
	public double calcTotalProductsDiscountAmount(List<Pet> pets, List<FeedAndOther> otherProducts) {
		return calcTotalProductsDiscountAmountTypeProduts(pets)
				+ calcTotalProductsDiscountAmountTypeProduts(otherProducts);
	}

	@Override
	public double calcTotalPersonDiscountAmount(double totalPaymentAmount, double totalProductsDiscountAmount,
			double personalDiscountProcent) {
		return Culculator.getInstance().calcProcentFromSum(totalPaymentAmount - totalProductsDiscountAmount,
				personalDiscountProcent);
	}

	@Override
	public double calcTotalDiscountAmount(double totalProductsDiscountAmount, double totalPersonDiscountAmount) {
		return totalProductsDiscountAmount + totalPersonDiscountAmount;
	}

	@Override
	public double calcTotalPaymentWithDiscountAmount(double totalPaymentAmount, double totalDiscountAmount) {
		return totalPaymentAmount - totalDiscountAmount;
	}

	private double calcTotalPaymentAmountTypeProduts(List<? extends AbstractProduct> products) {
		double totalPaymentAmount = 0;
		if (products != null) {
			for (AbstractProduct product : products) {
				totalPaymentAmount += product.getPrice();
			}
		}
		return totalPaymentAmount;
	}

	private double calcTotalProductsDiscountAmountTypeProduts(List<? extends AbstractProduct> products) {
		double totalProductsDiscountAmount = 0;
		if (products != null) {
			for (AbstractProduct product : products) {
				totalProductsDiscountAmount += Culculator.getInstance().calcProcentFromSum(product.getPrice(),
						product.getDiscount());
			}
		}
		return totalProductsDiscountAmount;
	}
}