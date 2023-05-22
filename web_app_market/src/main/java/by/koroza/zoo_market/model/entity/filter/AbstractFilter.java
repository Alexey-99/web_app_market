package by.koroza.zoo_market.model.entity.filter;

public abstract class AbstractFilter {
	private boolean onlyProductsWithDiscount;
	private double minDiscount;
	private double maxDiscount;
	private double minPrice;
	private double maxPrice;

	public AbstractFilter() {
		this.onlyProductsWithDiscount = false;
		this.minDiscount = 0;
		this.maxDiscount = 0;
		this.minPrice = 0;
		this.maxPrice = 0;
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

	public double getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + Boolean.hashCode(this.onlyProductsWithDiscount);
		result = PRIME * result + Double.hashCode(this.maxDiscount);
		result = PRIME * result + Double.hashCode(this.minDiscount);
		result = PRIME * result + Double.hashCode(this.maxPrice);
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
		AbstractFilter otherAbstractFilter = (AbstractFilter) object;
		if (this.onlyProductsWithDiscount != otherAbstractFilter.onlyProductsWithDiscount) {
			return false;
		}
		if (this.maxDiscount != otherAbstractFilter.maxDiscount) {
			return false;
		}
		if (this.minDiscount != otherAbstractFilter.minDiscount) {
			return false;
		}
		if (this.maxPrice != otherAbstractFilter.maxPrice) {
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
		if (this.onlyProductsWithDiscount == true) {
			builder.append("Only products with discount: ").append(onlyProductsWithDiscount).append("; ");
		} else {
			if (minDiscount != 0) {
				builder.append("min discount: ").append(minDiscount).append("; ");
			}
			if (maxDiscount != 0) {
				builder.append("max discount: ").append(maxDiscount).append("; ");
			}
		}
		if (minPrice != 0) {
			builder.append("min price: ").append(minPrice).append("; ");
		}
		if (maxPrice != 0) {
			builder.append("max price: ").append(maxPrice).append("; ");
		}
		return builder.toString();
	}

	public String toStringRus() {
		StringBuilder builder = new StringBuilder();
		if (this.onlyProductsWithDiscount == true) {
			builder.append("Только товары со скидками: ").append("да").append("; ");
		} else {
			if (minDiscount != 0) {
				builder.append("Минимальная скидка товара: ").append(minDiscount).append("%; ");
			}
			if (maxDiscount != 0) {
				builder.append("максимальная скидка товара: ").append(maxDiscount).append("%; ");
			}
		}
		if (minPrice != 0) {
			builder.append("Минимальцая цена: ").append(minPrice).append("; ");
		}
		if (maxPrice != 0) {
			builder.append("Максимальная цена: ").append(maxPrice).append("; ");
		}
		return builder.toString();
	}
}