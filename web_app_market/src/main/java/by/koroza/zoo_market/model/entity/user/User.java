package by.koroza.zoo_market.model.entity.user;

import by.koroza.zoo_market.model.entity.status.UserRole;

public class User {
	private static final int MAX_PROCENT_DISCOUNT = 50;
	private static final int QUANTITY_FOR_INCREASE_PERSONAL_PERSONAL_DISCOUNT = 50;
	private static final double PERCENT_FOR_EACH_QUANTITY_OF_PRODUCTS = 1;
	private long id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	private boolean isVerificatedEmail;
	private UserRole role;
	private double discount;

	public User() {
		this.name = null;
		this.surname = null;
		this.login = null;
		this.password = null;
		this.email = null;
		this.role = UserRole.REGISTRATING_USER;
		this.isVerificatedEmail = false;
		this.discount = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public UserRole getRole() {
		return this.role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.id);
		result = result * PRIME + (this.name != null ? this.name.hashCode() : 1);
		result = result * PRIME + (this.surname != null ? this.surname.hashCode() : 1);
		result = result * PRIME + (this.login != null ? this.login.hashCode() : 1);
		result = result * PRIME + (this.password != null ? this.password.hashCode() : 1);
		result = result * PRIME + (this.email != null ? this.email.hashCode() : 1);
		result = result * PRIME + Boolean.hashCode(this.isVerificatedEmail);
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
		if (this.name == null) {
			if (otherReservedUser.name != null) {
				return false;
			}
		} else if (!this.name.equals(otherReservedUser.name)) {
			return false;
		}
		if (this.surname == null) {
			if (otherReservedUser.surname != null) {
				return false;
			}
		} else if (!this.surname.equals(otherReservedUser.surname)) {
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
		builder.append(this.surname).append(" ").append(this.name);
		return builder.toString();
	}

	public static class UserBuilder {
		private User user;

		public UserBuilder() {
			this.user = new User();
		}

		public UserBuilder setId(long id) {
			this.user.setId(id);
			return this;
		}

		public UserBuilder setRole(UserRole role) {
			this.user.setRole(role);
			return this;
		}

		public UserBuilder setName(String name) {
			this.user.setName(name);
			return this;
		}

		public UserBuilder setSurname(String surname) {
			this.user.setSurname(surname);
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

		public UserBuilder setVerificatedEmail(boolean isVerificatedEmail) {
			this.user.setVerificatedEmail(isVerificatedEmail);
			return this;
		}

		public User build() {
			return this.user;
		}
	}
}