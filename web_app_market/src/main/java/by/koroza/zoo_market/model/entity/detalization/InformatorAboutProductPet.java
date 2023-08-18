package by.koroza.zoo_market.model.entity.detalization;

import by.koroza.zoo_market.model.entity.market.product.Pet;

public class InformatorAboutProductPet extends InformatorAboutProduct {
	private Pet pet;

	public InformatorAboutProductPet() {
		super();
		this.pet = null;
	}

	@Override
	public Pet getProduct() {
		return pet;
	}

	public void setProduct(Pet product) {
		this.pet = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pet == null) ? 0 : pet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		}
		InformatorAboutProductPet other = (InformatorAboutProductPet) object;
		if (pet == null) {
			if (other.pet != null) {
				return false;
			}
		} else if (!pet.equals(other.pet)) {
			return false;
		}
		return true;
	}
}