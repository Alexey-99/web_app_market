package by.koroza.zoo_market.model.entity.user;

import java.time.LocalDateTime;

import by.koroza.zoo_market.model.entity.status.UserRole;

public class User {
	private static final int MAX_PROCENT_DISCOUNT = 50;
	private static final int QUANTITY_FOR_INCREASE_PERSONAL_PERSONAL_DISCOUNT = 50;
	private static final double PERCENT_FOR_EACH_QUANTITY_OF_PRODUCTS = 1;
	private long id;
	private String login;
	private String password;
	private String email;
	private boolean isVerificatedEmail;
	private boolean isActive;
	private UserRole role;
	private double discount;
	private LocalDateTime dateAtCreate;

	public User() {
		this.login = null;
		this.password = null;
		this.email = null;
		this.role = UserRole.REGISTRATING_USER;
		this.isVerificatedEmail = false;
		this.isActive = true;
		this.discount = 0;
		dateAtCreate = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerificatedEmail() {
		return isVerificatedEmail;
	}

	public void setVerificatedEmail(boolean isVerificatedEmail) {
		this.isVerificatedEmail = isVerificatedEmail;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public void setRole(int roleId) {
		this.role = UserRole.findStatusByStatusId(roleId);
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public static int getMaxProcentDiscount() {
		return MAX_PROCENT_DISCOUNT;
	}

	public static int getQuantityForIncreasePersonalPersonalDiscount() {
		return QUANTITY_FOR_INCREASE_PERSONAL_PERSONAL_DISCOUNT;
	}

	public static double getPercentForEachQuantityOfProducts() {
		return PERCENT_FOR_EACH_QUANTITY_OF_PRODUCTS;
	}

	public LocalDateTime getDateAtCreate() {
		return this.dateAtCreate;
	}

	public void setDateAtCreate(LocalDateTime dateAtCreate) {
		this.dateAtCreate = dateAtCreate;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.id);
		result = result * PRIME + (this.login != null ? this.login.hashCode() : 1);
		result = result * PRIME + (this.password != null ? this.password.hashCode() : 1);
		result = result * PRIME + (this.email != null ? this.email.hashCode() : 1);
		result = result * PRIME + Boolean.hashCode(this.isVerificatedEmail);
		result = result * PRIME + Boolean.hashCode(this.isActive);
		result = result * PRIME + (this.role != null ? this.role.hashCode() : 1);
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
		User otherReservedUser = (User) object;
		if (this.id != otherReservedUser.id) {
			return false;
		}
		if (this.login == null) {
			if (otherReservedUser.login != null) {
				return false;
			}
		} else if (!this.login.equals(otherReservedUser.login)) {
			return false;
		}
		if (this.password == null) {
			if (otherReservedUser.password != null) {
				return false;
			}
		} else if (!this.password.equals(otherReservedUser.password)) {
			return false;
		}
		if (this.email == null) {
			if (otherReservedUser.email != null) {
				return false;
			}
		} else if (!this.email.equals(otherReservedUser.email)) {
			return false;
		}
		if (this.isVerificatedEmail != otherReservedUser.isVerificatedEmail) {
			return false;
		}
		if (this.isActive != otherReservedUser.isActive) {
			return false;
		}
		if (this.role == null) {
			if (otherReservedUser.role != null) {
				return false;
			}
		} else if (!this.role.equals(otherReservedUser.role)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", isVerificatedEmail=");
		builder.append(isVerificatedEmail);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", role=");
		builder.append(role);
		builder.append(", discount=");
		builder.append(discount);
		builder.append("]");
		return builder.toString();
	}

	public static class UserBuilder {
		private User user;

		public UserBuilder() {
			this.user = new User();
		}

		public UserBuilder setDateAtCreate(LocalDateTime dateAtCreate) {
			this.user.setDateAtCreate(dateAtCreate);
			return this;
		}

		public UserBuilder setId(long id) {
			this.user.setId(id);
			return this;
		}

		public UserBuilder setRole(UserRole role) {
			this.user.setRole(role);
			return this;
		}

		public UserBuilder setRole(int roleId) {
			this.user.setRole(roleId);
			return this;
		}

		public UserBuilder setLogin(String login) {
			this.user.setLogin(login);
			return this;
		}

		public UserBuilder setDiscount(double discount) {
			this.user.setDiscount(discount);
			return this;
		}

		public UserBuilder setEmail(String email) {
			this.user.setEmail(email);
			return this;
		}

		public UserBuilder setActive(boolean isActive) {
			this.user.setActive(isActive);
			return this;
		}

		public UserBuilder setVerificatedEmail(boolean isVerificatedEmail) {
			this.user.setVerificatedEmail(isVerificatedEmail);
			return this;
		}

		public User build() {
			return this.user;
		}
	}
}