package by.koroza.zoo_market.dao.pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

import by.koroza.zoo_market.dao.exception.uncheckable.MySqlDataSourceeException;

/**
 * A factory for creating MySqlDataSource objects.
 */
public class MySqlDataSourceFactory {

	/** The Constant LOGGER. */
	private static final Logger log = LogManager.getLogger();

	/** The Constant DATABASE_PROPERTIES_PATH. */
	private static final String DATABASE_PROPERTIES_PATH = "database_properties/property.txt";;

	/** The Constant PROPERTY_DATABASE_URL. */
	private static final String PROPERTY_DATABASE_URL = "db.url";

	/** The Constant PROPERTY_DATABASE_USER. */
	private static final String PROPERTY_DATABASE_USER = "db.user";

	/** The Constant PROPERTY_DATABASE_PASS. */
	private static final String PROPERTY_DATABASE_PASS = "db.pass";

	/** The properties. */
	private static Properties properties;

	static {
		InputStream inputStream = MySqlDataSourceFactory.class.getClassLoader()
				.getResourceAsStream(DATABASE_PROPERTIES_PATH);
		try {
			properties = new Properties();
			properties.load(inputStream);
		} catch (IOException e) {
			log.log(Level.FATAL, "This file isn't readable by path: " + DATABASE_PROPERTIES_PATH
					+ " because pathProperties is null");
			throw new MySqlDataSourceeException("This file isn't readable by path: " + DATABASE_PROPERTIES_PATH
					+ " because pathProperties is null");
		}
	}

	/**
	 * Creates a new MySqlDataSource object.
	 *
	 * @return the mysql data source
	 */
	public static MysqlDataSource createMySqlDataSource() {
		MysqlDataSource dataSource = null;
		dataSource = new MysqlDataSource();
		dataSource.setUrl(properties.getProperty(PROPERTY_DATABASE_URL));
		dataSource.setUser(properties.getProperty(PROPERTY_DATABASE_USER));
		dataSource.setPassword(properties.getProperty(PROPERTY_DATABASE_PASS));
		return dataSource;
	}
}