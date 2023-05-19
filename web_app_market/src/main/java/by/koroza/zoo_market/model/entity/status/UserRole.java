package by.koroza.zoo_market.model.entity.status;

public enum UserRole {
	REGISTRATING_USER(0), WAITING_CODE_REGISTRATION(1), USER(2), ADMIN(3);

	private int indexRole;

	private UserRole(int indexRole) {
		this.indexRole = indexRole;
	}

	public int getIdRole() {
		return indexRole;
	}
}