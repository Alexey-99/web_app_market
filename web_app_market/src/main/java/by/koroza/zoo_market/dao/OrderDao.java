package by.koroza.zoo_market.dao;

import java.util.List;

import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.model.entity.market.order.Order;

public interface OrderDao {

	public List<Order> getOrderProductsByUserId(long userId) throws DaoException;

	public boolean addOrder(Order order, long userId) throws DaoException;
}