package by.koroza.zoo_market.dao.impl.bank;

import static by.koroza.zoo_market.dao.name.ColumnName.IDENTIFIER_COUNT_ROWS_OF_BANK_CARDS_ID;
import static by.koroza.zoo_market.dao.name.ColumnName.BANK_CARDS_SUM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.koroza.zoo_market.dao.pool.ConnectionPool;
import by.koroza.zoo_market.dao.BankCardDao;
import by.koroza.zoo_market.dao.exception.DaoException;
import by.koroza.zoo_market.dao.pool.ProxyConnection;
import by.koroza.zoo_market.model.entity.bank.BankCard;

public class BankCardDaoImpl implements BankCardDao {
	private static final BankCardDao INSTANCE = new BankCardDaoImpl();

	private BankCardDaoImpl() {
	}

	public static BankCardDao getInstance() {
		return INSTANCE;
	}

	private static final String QUERY_SELECT_COUNT_BANK_CARD = """
			SELECT COUNT(bank_cards.id)
			FROM bank_cards
			WHERE number = ? AND month_end = ? AND year_end = ? AND cvc = ?;
			""";

	@Override
	public boolean isHaveBankCard(BankCard card) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_COUNT_BANK_CARD)) {
			statement.setString(1, card.getNumberCard());
			statement.setInt(2, card.getMonthEnd());
			statement.setInt(3, card.getYearEnd());
			statement.setInt(4, card.getCVC());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(IDENTIFIER_COUNT_ROWS_OF_BANK_CARDS_ID) > 0;
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}

	private static final String QUERY_SELECT_SUM_BANK_CARD = """
			SELECT sum
			FROM bank_cards
			WHERE number = ? AND month_end = ? AND year_end = ? AND cvc = ?;
			""";

	@Override
	public boolean isHaveSumOnBankCard(BankCard card, double price) throws DaoException {
		boolean result = false;
		try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
				PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_SUM_BANK_CARD)) {
			statement.setString(1, card.getNumberCard());
			statement.setInt(2, card.getMonthEnd());
			statement.setInt(3, card.getYearEnd());
			statement.setInt(4, card.getCVC());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					result = resultSet.getInt(BANK_CARDS_SUM) >= price;
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return result;
	}
}