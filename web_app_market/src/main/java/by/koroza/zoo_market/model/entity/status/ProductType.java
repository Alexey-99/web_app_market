package by.koroza.zoo_market.model.entity.status;

public enum ProductType {
	PETS(1), FEEDS_AND_OTHER(2);

	private int id;

	private ProductType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}