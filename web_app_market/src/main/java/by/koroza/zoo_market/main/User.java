package by.koroza.zoo_market.main;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -3733782742070723489L;
	private int id;
	transient private String name; // не должны быть в методе hashCode()
	//private static int staticField;

	public User(int id, String name) {
		this.id = id;
		this.name = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}