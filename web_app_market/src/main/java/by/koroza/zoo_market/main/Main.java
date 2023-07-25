package by.koroza.zoo_market.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.pool.ProxyConnection;

public class Main {

	public static void main(String[] args) {
		try {
			ProxyConnection connection = 
					ConnectionPool.INSTANCE.getConnection();
//			...
			connection.close();
		} catch (SQLException e) {
//			 ...
		}
		try (ProxyConnection connection2 = ConnectionPool.INSTANCE.getConnection()) {
//			...
		} catch (SQLException e) {
//			 ...
		}
	}
}