package by.koroza.zoo_market.model.entity.user.abstraction;

import by.koroza.zoo_market.model.entity.status.UserRole;

public abstract class AbstractRegistratedUser {
	private long id;
	private String name;
	private String surname;
	private String patronymic;
	private String login;
	private String password;
	private String email;
	private UserRole role;
	private double discount;

	public AbstractRegistratedUser() {
		this.name = null;
		this.surname = null;
		this.patronymic = null;
		this.login = null;
		this.password = null;
		this.email = null;
		this.role = UserRole.REGISTRATING_USER;
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

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
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

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + Long.hashCode(this.id);
		result = result * PRIME + (this.name != null ? this.name.hashCode() : 1);
		result = result * PRIME + (this.surname != null ? this.surname.hashCode() : 1);
		result = result * PRIME + (this.patronymic != null ? this.patronymic.hashCode() : 1);
		result = result * PRIME + (this.login != null ? this.login.hashCode() : 1);
		result = result * PRIME + (this.password != null ? this.password.hashCode() : 1);
		result = result * PRIME + (this.email != null ? this.email.hashCode() : 1);
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
		AbstractRegistratedUser otherReservedUser = (AbstractRegistratedUser) object;
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
		if (this.patronymic == null) {
			if (otherReservedUser.patronymic != null) {
				return false;
			}
		} else if (!this.patronymic.equals(otherReservedUser.patronymic)) {
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
		builder.append(this.surname).append(" ").append(this.name).append(" ").append(this.patronymic);
		return builder.toString();
	}
}