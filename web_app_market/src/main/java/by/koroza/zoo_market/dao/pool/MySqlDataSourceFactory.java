package by.koroza.zoo_market.dao.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlDataSourceFactory {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String DATABASE_PROPERTIES_PATH = "database_properties/property.txt";;
	private static final String PROPERTY_DATABASE_URL = "db.url";
	private static final String PROPERTY_DATABASE_USER = "db.user";
	private static final String PROPERTY_DATABASE_PASS = "db.pass";

	private static Properties properties;

	static {
		InputStream inputStream = MySqlDataSourceFactory.class.getClassLoader()
				.getResourceAsStream(DATABASE_PROPERTIES_PATH);
		try {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			LOGGER.log(Level.FATAL, "This file isn't readable by path: " + DATABASE_PROPERTIES_PATH
					+ " because pathProperties is null");
			throw new RuntimeException("This file isn't readable by path: " + DATABASE_PROPERTIES_PATH
					+ " because pathProperties is null");
		}
	}

	public static MysqlDataSource createMySqlDataSource() {
		MysqlDataSource dataSource = null;
		dataSource = new MysqlDataSource();
		dataSource.setUrl(properties.getProperty(PROPERTY_DATABASE_URL));
		dataSource.setUser(properties.getProperty(PROPERTY_DATABASE_USER));
		dataSource.setPassword(properties.getProperty(PROPERTY_DATABASE_PASS));
		return dataSource;
	}
}