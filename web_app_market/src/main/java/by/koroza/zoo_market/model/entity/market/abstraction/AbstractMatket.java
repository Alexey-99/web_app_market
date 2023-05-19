package by.koroza.zoo_market.model.entity.market.abstraction;

public abstract class AbstractMatket {
	private String name;

	public AbstractMatket() {
		this.name = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + (this.name != null ? this.name.hashCode() : 1);
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
		AbstractMatket otherAbstractMatket = (AbstractMatket) object;
		if (this.name == null) {
			if (otherAbstractMatket.name != null) {
				return false;
			}
		} else if (!this.name.equals(otherAbstractMatket.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Market name: ").append(this.name != null && !this.name.isBlank() ? this.name : "without name");
		return builder.toString();
	}
}