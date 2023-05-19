package by.koroza.zoo_market.model.entity.filter;

public abstract class AbstractFilter {
	private boolean onlyProductsWithDiscont;
	private double minDiscont;
	private double maxDiscont;
	private double minPrice;
	private double maxPrice;

	public AbstractFilter() {
		this.onlyProductsWithDiscont = false;
		this.minDiscont = 0;
		this.maxDiscont = 0;
		this.minPrice = 0;
		this.maxPrice = 0;
	}

	public boolean isOnlyProductsWithDiscont() {
		return this.onlyProductsWithDiscont;
	}

	public void setOnlyProductsWithDiscont(boolean onlyProductsWithDiscont) {
		this.onlyProductsWithDiscont = onlyProductsWithDiscont;
	}

	public double getMinDiscont() {
		return this.minDiscont;
	}

	public void setMinDiscont(double minDiscont) {
		this.minDiscont = minDiscont;
	}

	public double getMaxDiscont() {
		return this.maxDiscont;
	}

	public void setMaxDiscont(double maxDiscont) {
		this.maxDiscont = maxDiscont;
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
		result = PRIME * result + Boolean.hashCode(this.onlyProductsWithDiscont);
		result = PRIME * result + Double.hashCode(this.maxDiscont);
		result = PRIME * result + Double.hashCode(this.minDiscont);
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
		if (this.onlyProductsWithDiscont != otherAbstractFilter.onlyProductsWithDiscont) {
			return false;
		}
		if (this.maxDiscont != otherAbstractFilter.maxDiscont) {
			return false;
		}
		if (this.minDiscont != otherAbstractFilter.minDiscont) {
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
		builder.append("AbstractFilter [onlyProductsWithDiscont=");
		builder.append(onlyProductsWithDiscont);
		builder.append(", minDiscont=");
		builder.append(minDiscont);
		builder.append(", maxDiscont=");
		builder.append(maxDiscont);
		builder.append(", minPrice=");
		builder.append(minPrice);
		builder.append(", maxPrice=");
		builder.append(maxPrice);
		builder.append("]");
		return builder.toString();
	}
}