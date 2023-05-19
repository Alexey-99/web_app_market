package by.koroza.zoo_market.model.entity.user.reserved;

import by.koroza.zoo_market.model.entity.status.UserRole;
import by.koroza.zoo_market.model.entity.user.abstraction.AbstractRegistratedUser;

public class Administator extends AbstractRegistratedUser {

	public Administator() {
		super.setRole(UserRole.ADMIN);
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

	public static class AdministatorBuilder {
		private Administator administator;

		public AdministatorBuilder() {
			this.administator = new Administator();
		}

		public AdministatorBuilder setRole(UserRole role) {
			this.administator.setRole(role);
			return this;
		}

		public AdministatorBuilder setName(String name) {
			this.administator.setName(name);
			return this;
		}

		public AdministatorBuilder setSurname(String surname) {
			this.administator.setSurname(surname);
			return this;
		}

		public AdministatorBuilder setPatronymic(String patronymic) {
			this.administator.setPatronymic(patronymic);
			return this;
		}

		public AdministatorBuilder setLogin(String login) {
			this.administator.setLogin(login);
			return this;
		}

		public AdministatorBuilder setEmail(String email) {
			this.administator.setEmail(email);
			return this;
		}

		public Administator build() {
			return this.administator;
		}
	}
}