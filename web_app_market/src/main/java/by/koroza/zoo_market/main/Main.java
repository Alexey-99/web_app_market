package by.koroza.zoo_market.main;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;

public class Main {

	public static void main(String[] args) {

		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statment = connection.prepareStatement("")) {

//			...
		} catch (SQLException e) {
//			 ...
		}
	}
}