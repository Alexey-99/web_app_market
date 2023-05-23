package by.koroza.zoo_market.model.entity.user.reserved;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;

public class User extends AbstractRegistratedUser {

	public User() {
		super();
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
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

		public UserBuilder setPatronymic(String patronymic) {
			this.user.setPatronymic(patronymic);
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