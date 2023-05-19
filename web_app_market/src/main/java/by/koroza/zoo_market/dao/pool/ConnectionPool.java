package by.koroza.zoo_market.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.sql.DataSource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ConnectionPool {
	INSTANCE;

	private final Logger LOGGER = LogManager.getLogger();

	private final int DEFAULT_POOL_SISE = 4;

	private DataSource dataSource;

	private BlockingQueue<ProxyConnection> freeConnections;
	private BlockingQueue<ProxyConnection> bisyConnections;

	{
		dataSource = MySqlDataSourceFactory.createMySqlDataSource();
	}

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {
		this.freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SISE);
		this.bisyConnections = new LinkedBlockingQueue<>();
		initializeConnectionPool();
	}

	/**
	 * Get the connection.
	 *
	 * @return the connection
	 */
	public ProxyConnection getConnection() {
		ProxyConnection connection = null;
		try {
			connection = this.freeConnections.take();
			this.bisyConnections.offer(connection);
		} catch (InterruptedException e) {
			LOGGER.log(Level.ERROR, "error in method getConnection(): " + e);
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 * @return true, if connection was released
	 */
	public boolean releaseConnection(ProxyConnection connection) {
		boolean result = true;
		if (connection instanceof ProxyConnection) {
			this.bisyConnections.remove(connection);
			this.freeConnections.offer(connection);
		} else {
			result = false;
			LOGGER.log(Level.ERROR, "error in method releaseConnection()");
		}
		return result;
	}

	/**
	 * Destroy pool.
	 *
	 * @return true, if connection pool was destroyed
	 */
	public void destroyPool() {
		for (int i = 0; i < DEFAULT_POOL_SISE; i++) {
			try {
				this.freeConnections.take().reallyClose();
			} catch (SQLException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		deregisterDrivers();
	}

	/**
	 * Deregister drivers.
	 */
	private void deregisterDrivers() {
		DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Initialize connection pool.
	 */
	private void initializeConnectionPool() {
		for (int i = 0; i < DEFAULT_POOL_SISE; i++) {
			try {
				Connection connection = dataSource.getConnection();
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.offer(proxyConnection);
			} catch (SQLException e) {
				LOGGER.log(Level.FATAL, "connection does not create.", e);
				throw new RuntimeException("Fatal error. Connection does not create", e);
			}
		}
	}
}