package by.koroza.zoo_market.model.entity.detalization;

import by.koroza.zoo_market.model.entity.status.OrderStatus;

public class OrderDetalizationByProduct {
	private long orderId;
	private long quantityProduct;
	private double totalSum;
	private long userId;
	private String userLogin;
	private OrderStatus status;

	public OrderDetalizationByProduct(long orderId, long quantityProduct, double totalSum, long userId,
			String userLogin, int orderStatusId) {
		this.orderId = orderId;
		this.quantityProduct = quantityProduct;
		this.totalSum = totalSum;
		this.userId = userId;
		this.userLogin = userLogin;
		this.status = OrderStatus.findStatusByStatusId(orderStatusId);
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getQuantityProduct() {
		return quantityProduct;
	}

	public void setQuantityProduct(long quantityProduct) {
		this.quantityProduct = quantityProduct;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.orderId);
		result = result * PRIME + Long.hashCode(this.quantityProduct);
		result = result * PRIME + Double.hashCode(this.totalSum);
		result = result * PRIME + Long.hashCode(this.userId);
		result = result * PRIME + (this.userLogin != null ? this.userLogin.hashCode() : 1);
		result = result * PRIME + (this.status != null ? this.status.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		OrderDetalizationByProduct other = (OrderDetalizationByProduct) object;
		if (this.orderId != other.orderId) {
			return false;
		}
		if (this.quantityProduct != other.quantityProduct) {
			return false;
		}
		if (this.totalSum != other.totalSum) {
			return false;
		}
		if (this.userId != other.userId) {
			return false;
		}
		if (this.userLogin == null) {
			if (other.userLogin != null) {
				return false;
			}
		} else if (!this.userLogin.equals(other.userLogin)) {
			return false;
		}
		if (this.status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!this.status.equals(other.status)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDetalizationByProduct [orderId=");
		builder.append(orderId);
		builder.append(", quantityProduct=");
		builder.append(quantityProduct);
		builder.append(", totalSum=");
		builder.append(totalSum);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userLogin=");
		builder.append(userLogin);
		builder.append("]");
		return builder.toString();
	}
}