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

import by.koroza.zoo_market.dao.exception.uncheckable.ConnectionPoolException;

/**
 * The Enum ConnectionPool.
 */
public enum ConnectionPool {

	/** The instance. */
	INSTANCE;

	/** The logger. */
	private Logger log = LogManager.getLogger();

	/** The default pool size. */
	private final int DEFAULT_POOL_SIZE = 4;

	/** The data source. */
	private DataSource dataSource;

	/** The free connections. */
	private BlockingQueue<ProxyConnection> freeConnections;

	/** The busy connections. */
	private BlockingQueue<ProxyConnection> busyConnections;

	{
		dataSource = MySqlDataSourceFactory.createMySqlDataSource();
	}

	/**
	 * Instantiates a new connection pool.
	 */
	private ConnectionPool() {
		this.freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
		this.busyConnections = new LinkedBlockingQueue<>();
		initializeConnectionPool();
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public ProxyConnection getConnection() {
		ProxyConnection connection = null;
		try {
			connection = this.freeConnections.take();
			this.busyConnections.offer(connection);
		} catch (InterruptedException e) {
			log.log(Level.ERROR, "error in method getConnection(): " + e);
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 * @return true, if successful
	 */
	public boolean releaseConnection(ProxyConnection connection) {
		boolean result = true;
		if (connection instanceof ProxyConnection) {
			this.busyConnections.remove(connection);
			this.freeConnections.offer(connection);
		} else {
			result = false;
			log.log(Level.ERROR, "error in method releaseConnection()");
		}
		return result;
	}

	/**
	 * Destroy pool.
	 */
	public void destroyPool() {
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				this.freeConnections.take().reallyClose();
			} catch (SQLException | InterruptedException e) {
				log.log(Level.ERROR, e.getMessage());
				Thread.currentThread().interrupt();
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
		for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
			try {
				Connection connection = dataSource.getConnection();
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				freeConnections.offer(proxyConnection);
			} catch (SQLException e) {
				log.log(Level.FATAL, "connection does not create.", e);
				throw new ConnectionPoolException("Fatal error. Connection does not create", e);
			}
		}
	}
}