package by.koroza.zoo_market.service;

import by.koroza.zoo_market.service.exception.ServiceException;

public interface ProductService {
//	public List<? extends AbstractProduct> getAllHavingProducts() throws ServiceException;

	public String getProductImagePathByProductId(long id) throws ServiceException;

	public boolean transferProductFromMarketToOrder(long productId, long orderId) throws ServiceException;

	public boolean transferProductFromOrderToMarket(long productId, long orderId) throws ServiceException;

	public long getFreeNumberOfUnitsByProductId(long productId) throws ServiceException;

	public long getQuantityInOrdersByProductIdAndOrderStatus(long productId, int orderStatusId) throws ServiceException;
}
