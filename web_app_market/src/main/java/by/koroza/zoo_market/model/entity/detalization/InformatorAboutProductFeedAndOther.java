package by.koroza.zoo_market.model.entity.detalization;

import by.koroza.zoo_market.model.entity.market.product.FeedAndOther;

public class InformatorAboutProductFeedAndOther extends InformatorAboutProduct {
	private FeedAndOther product;

	public InformatorAboutProductFeedAndOther() {
		super();
		this.product = null;
	}

	@Override
	public FeedAndOther getProduct() {
		return product;
	}

	public void setProduct(FeedAndOther product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = result * PRIME + (this.product != null ? this.product.hashCode() : 1);
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		}
		InformatorAboutProductFeedAndOther other = (InformatorAboutProductFeedAndOther) object;
		if (this.product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!this.product.equals(other.product)) {
			return false;
		}
		return true;
	}
}