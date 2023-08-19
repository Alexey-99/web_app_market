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

	public static UserRole findStatusByStatusId(int id) throws IllegalArgumentException {
		UserRole role = null;
		switch (id) {
		case 1 -> role = WAITING_CODE_REGISTRATION;
		case 2 -> role = USER;
		case 3 -> role = ADMIN;
		default -> throw new IllegalArgumentException("Unexpected value: " + id);
		}
		return role;
	}

	public static boolean isHaveRoleById(int id) {
		boolean result = false;
		for (UserRole role : UserRole.values()) {
			if (role.getId() == id) {
				result = true;
			}
		}
		return result;
	}
}