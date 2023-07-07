package by.koroza.zoo_market.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.ShardingKey;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

// TODO: Auto-generated Javadoc
/**
 * The Class ProxyConnection.
 */
public class ProxyConnection implements Connection {

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new proxy connection.
	 *
	 * @param connection the connection
	 */
	ProxyConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Unwrap.
	 *
	 * @param <T>   the generic type
	 * @param iface the iface
	 * @return the t
	 * @throws SQLException the SQL exception
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.connection.unwrap(iface);
	}

	/**
	 * Checks if is wrapper for.
	 *
	 * @param iface the iface
	 * @return true, if is wrapper for
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.connection.isWrapperFor(iface);
	}

	/**
	 * Creates the statement.
	 *
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Statement createStatement() throws SQLException {
		return this.connection.createStatement();
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql the sql
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return this.connection.prepareStatement(sql);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql the sql
	 * @return the callable statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return this.connection.prepareCall(sql);
	}

	/**
	 * Native SQL.
	 *
	 * @param sql the sql
	 * @return the string
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String nativeSQL(String sql) throws SQLException {
		return this.connection.nativeSQL(sql);
	}

	/**
	 * Set the auto commit.
	 *
	 * @param autoCommit the new auto commit
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		this.connection.setAutoCommit(autoCommit);
	}

	/**
	 * Get the auto commit.
	 *
	 * @return the auto commit
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean getAutoCommit() throws SQLException {
		return this.connection.getAutoCommit();
	}

	/**
	 * Commit.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void commit() throws SQLException {
		this.connection.commit();
	}

	/**
	 * Rollback.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void rollback() throws SQLException {
		this.connection.rollback();
	}

	/**
	 * Close.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void close() throws SQLException {
		ConnectionPool.INSTANCE.releaseConnection(this);
	}

	/**
	 * Really close.
	 *
	 * @throws SQLException the SQL exception
	 */
	void reallyClose() throws SQLException {
		this.connection.close();
	}

	/**
	 * Check if is closed.
	 *
	 * @return true, if is closed
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isClosed() throws SQLException {
		return this.connection.isClosed();
	}

	/**
	 * Get the meta data.
	 *
	 * @return the meta data
	 * @throws SQLException the SQL exception
	 */
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return this.connection.getMetaData();
	}

	/**
	 * Set the read only.
	 *
	 * @param readOnly the new read only
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		this.connection.setReadOnly(readOnly);
	}

	/**
	 * Check if is read only.
	 *
	 * @return true, if is read only
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isReadOnly() throws SQLException {
		return this.connection.isReadOnly();
	}

	/**
	 * Set the catalog.
	 *
	 * @param catalog the new catalog
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setCatalog(String catalog) throws SQLException {
		this.connection.setCatalog(catalog);
	}

	/**
	 * Get the catalog.
	 *
	 * @return the catalog
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String getCatalog() throws SQLException {
		return this.connection.getCatalog();
	}

	/**
	 * Set the transaction isolation.
	 *
	 * @param level the new transaction isolation
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		this.connection.setTransactionIsolation(level);
	}

	/**
	 * Get the transaction isolation.
	 *
	 * @return the transaction isolation
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getTransactionIsolation() throws SQLException {
		return this.connection.getTransactionIsolation();
	}

	/**
	 * Get the warnings.
	 *
	 * @return the warnings
	 * @throws SQLException the SQL exception
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		return this.connection.getWarnings();
	}

	/**
	 * Clear warnings.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void clearWarnings() throws SQLException {
		this.connection.clearWarnings();
	}

	/**
	 * Create the statement.
	 *
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return this.connection.createStatement(resultSetType, resultSetConcurrency);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @return the callable statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * Get the type map.
	 *
	 * @return the type map
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return this.connection.getTypeMap();
	}

	/**
	 * Set the type map.
	 *
	 * @param map the map
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		this.connection.setTypeMap(map);
	}

	/**
	 * Set the holdability.
	 *
	 * @param holdability the new holdability
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setHoldability(int holdability) throws SQLException {
		this.connection.setHoldability(holdability);
	}

	/**
	 * Get the holdability.
	 *
	 * @return the holdability
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getHoldability() throws SQLException {
		return this.connection.getHoldability();
	}

	/**
	 * Set the savepoint.
	 *
	 * @return the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Savepoint setSavepoint() throws SQLException {
		return this.connection.setSavepoint();
	}

	/**
	 * Set the savepoint.
	 *
	 * @param name the name
	 * @return the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return this.connection.setSavepoint(name);
	}

	/**
	 * Rollback.
	 *
	 * @param savepoint the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		this.connection.rollback(savepoint);
	}

	/**
	 * Release savepoint.
	 *
	 * @param savepoint the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		this.connection.releaseSavepoint(savepoint);
	}

	/**
	 * Create the statement.
	 *
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @param resultSetHoldability the result set holdability
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return this.connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @param resultSetHoldability the result set holdability
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return this.connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @param resultSetHoldability the result set holdability
	 * @return the callable statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return this.connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql               the sql
	 * @param autoGeneratedKeys the auto generated keys
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return this.connection.prepareStatement(sql, autoGeneratedKeys);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql           the sql
	 * @param columnIndexes the column indexes
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return this.connection.prepareStatement(sql, columnIndexes);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql         the sql
	 * @param columnNames the column names
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return this.connection.prepareStatement(sql, columnNames);
	}

	/**
	 * Create the clob.
	 *
	 * @return the clob
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Clob createClob() throws SQLException {
		return this.connection.createClob();
	}

	/**
	 * Create the blob.
	 *
	 * @return the blob
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Blob createBlob() throws SQLException {
		return this.connection.createBlob();
	}

	/**
	 * Create the N clob.
	 *
	 * @return the n clob
	 * @throws SQLException the SQL exception
	 */
	@Override
	public NClob createNClob() throws SQLException {
		return this.connection.createNClob();
	}

	/**
	 * Create the SQLXML.
	 *
	 * @return the sqlxml
	 * @throws SQLException the SQL exception
	 */
	@Override
	public SQLXML createSQLXML() throws SQLException {
		return this.connection.createSQLXML();
	}

	/**
	 * Check if is valid.
	 *
	 * @param timeout the timeout
	 * @return true, if is valid
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isValid(int timeout) throws SQLException {
		return this.connection.isValid(timeout);
	}

	/**
	 * Set the client info.
	 *
	 * @param name  the name
	 * @param value the value
	 * @throws SQLClientInfoException the SQL client info exception
	 */
	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		this.connection.setClientInfo(name, value);
	}

	/**
	 * Set the client info.
	 *
	 * @param properties the new client info
	 * @throws SQLClientInfoException the SQL client info exception
	 */
	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		this.connection.setClientInfo(properties);
	}

	/**
	 * Get the client info.
	 *
	 * @param name the name
	 * @return the client info
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String getClientInfo(String name) throws SQLException {
		return this.connection.getClientInfo(name);
	}

	/**
	 * Get the client info.
	 *
	 * @return the client info
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Properties getClientInfo() throws SQLException {
		return this.connection.getClientInfo();
	}

	/**
	 * Create the array of.
	 *
	 * @param typeName the type name
	 * @param elements the elements
	 * @return the array
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return this.connection.createArrayOf(typeName, elements);
	}

	/**
	 * Create the struct.
	 *
	 * @param typeName   the type name
	 * @param attributes the attributes
	 * @return the struct
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return this.connection.createStruct(typeName, attributes);
	}

	/**
	 * Set the schema.
	 *
	 * @param schema the new schema
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setSchema(String schema) throws SQLException {
		this.connection.setSchema(schema);
	}

	/**
	 * Get the schema.
	 *
	 * @return the schema
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String getSchema() throws SQLException {
		return this.connection.getSchema();
	}

	/**
	 * Abort.
	 *
	 * @param executor the executor
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void abort(Executor executor) throws SQLException {
		this.connection.abort(executor);
	}

	/**
	 * Set the network timeout.
	 *
	 * @param executor     the executor
	 * @param milliseconds the milliseconds
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		this.connection.setNetworkTimeout(executor, milliseconds);
	}

	/**
	 * Get the network timeout.
	 *
	 * @return the network timeout
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getNetworkTimeout() throws SQLException {
		return this.connection.getNetworkTimeout();
	}

	/**
	 * Begin request.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void beginRequest() throws SQLException {
		this.connection.beginRequest();
	}

	/**
	 * End request.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void endRequest() throws SQLException {
		this.connection.endRequest();
	}

	/**
	 * Set the sharding key if valid.
	 *
	 * @param shardingKey      the sharding key
	 * @param superShardingKey the super sharding key
	 * @param timeout          the timeout
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout)
			throws SQLException {
		return this.connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
	}

	/**
	 * Set the sharding key if valid.
	 *
	 * @param shardingKey the sharding key
	 * @param timeout     the timeout
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
		return this.connection.setShardingKeyIfValid(shardingKey, timeout);
	}

	/**
	 * Set the sharding key.
	 *
	 * @param shardingKey      the sharding key
	 * @param superShardingKey the super sharding key
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
		this.connection.setShardingKey(shardingKey, superShardingKey);
	}

	/**
	 * Set the sharding key.
	 *
	 * @param shardingKey the new sharding key
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setShardingKey(ShardingKey shardingKey) throws SQLException {
		this.connection.setShardingKey(shardingKey);
	}
}