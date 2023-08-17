package by.koroza.zoo_market.model.entity.status;

public enum UserRole {
	REGISTRATING_USER(0), WAITING_CODE_REGISTRATION(1), USER(2), ADMIN(3);

	private int id;

	private UserRole(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
}