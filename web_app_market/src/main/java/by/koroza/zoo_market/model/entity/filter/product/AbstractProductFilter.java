package by.koroza.zoo_market.model.entity.filter.product;

public abstract class AbstractProductFilter {
	public static final double MAX_DISCOUNT = 100;
	public static final double MIN_DISCOUNT = 0;
	public static final double MIN_PRICE = 0;

	private boolean onlyProductsWithDiscount;
	private double minDiscount;
	private double maxDiscount;
	private double minPrice;
	private double maxPriceAllProducts;
	private double maxPriceEntered;

	public AbstractProductFilter() {
		this.onlyProductsWithDiscount = false;
		this.minDiscount = MIN_DISCOUNT;
		this.maxDiscount = MAX_DISCOUNT;
		this.minPrice = MIN_PRICE;
		this.maxPriceAllProducts = MIN_PRICE;
	}

	public boolean isOnlyProductsWithDiscount() {
		return this.onlyProductsWithDiscount;
	}

	public void setOnlyProductsWithDiscont(boolean onlyProductsWithDiscont) {
		this.onlyProductsWithDiscount = onlyProductsWithDiscont;
	}

	public double getMinDiscount() {
		return this.minDiscount;
	}

	public void setMinDiscount(double minDiscount) {
		this.minDiscount = minDiscount;
	}

	public double getMaxDiscount() {
		return this.maxDiscount;
	}

	public void setMaxDiscount(double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public double getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPriceAllProducts() {
		return maxPriceAllProducts;
	}

	public void setMaxPriceAllProducts(double maxPriceAllProducts) {
		this.maxPriceAllProducts = maxPriceAllProducts;
	}

	public double getMaxPriceEntered() {
		return maxPriceEntered;
	}

	public void setMaxPriceEntered(double maxPriceEntered) {
		this.maxPriceEntered = maxPriceEntered;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + Boolean.hashCode(this.onlyProductsWithDiscount);
		result = PRIME * result + Double.hashCode(this.maxDiscount);
		result = PRIME * result + Double.hashCode(this.minDiscount);
		result = PRIME * result + Double.hashCode(this.maxPriceAllProducts);
		result = PRIME * result + Double.hashCode(this.maxPriceEntered);
		result = PRIME * result + Double.hashCode(this.minPrice);
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
		if (!getClass().equals(object.getClass())) {
			return false;
		}
		AbstractProductFilter otherAbstractFilter = (AbstractProductFilter) object;
		if (this.onlyProductsWithDiscount != otherAbstractFilter.onlyProductsWithDiscount) {
			return false;
		}
		if (this.maxDiscount != otherAbstractFilter.maxDiscount) {
			return false;
		}
		if (this.minDiscount != otherAbstractFilter.minDiscount) {
			return false;
		}
		if (this.maxPriceAllProducts != otherAbstractFilter.maxPriceAllProducts) {
			return false;
		}
		if (this.maxPriceEntered != otherAbstractFilter.maxPriceEntered) {
			return false;
		}
		if (this.minPrice != otherAbstractFilter.minPrice) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (this.onlyProductsWithDiscount) {
			builder.append("Only products with discount: ").append(onlyProductsWithDiscount).append("; ");
		} else {
			if (minDiscount > MIN_DISCOUNT) {
				builder.append("min discount: ").append(minDiscount).append("; ");
			}
			if (maxDiscount < MAX_DISCOUNT) {
				builder.append("max discount: ").append(maxDiscount).append("; ");
			}
		}
		if (minPrice > MIN_PRICE) {
			builder.append("min price: ").append(minPrice).append("; ");
		}

		if (this.maxPriceEntered != this.maxPriceAllProducts) {
			builder.append("max price: ").append(this.maxPriceEntered).append("; ");
		}
		return builder.toString();
	}

	public String toStringRus() {
		StringBuilder builder = new StringBuilder();
		if (this.onlyProductsWithDiscount) {
			builder.append("Только товары со скидками: ").append("да").append("; ");
		} else {
			if (minDiscount > MIN_DISCOUNT) {
				builder.append("Минимальная скидка товара: ").append(minDiscount).append("%; ");
			}
			if (maxDiscount < MAX_DISCOUNT) {
				builder.append("максимальная скидка товара: ").append(maxDiscount).append("%; ");
			}
		}
		if (minPrice > MIN_PRICE) {
			builder.append("Минимальцая цена: ").append(minPrice).append("; ");
		}
		if (this.maxPriceEntered != this.maxPriceAllProducts) {
			builder.append("Максимальная цена: ").append(this.maxPriceEntered).append("; ");
		}
		return builder.toString();
	}
}