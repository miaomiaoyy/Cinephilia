package reviewApp.dal;

import reviewApp.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Yang on 2017/11/02
 */

public class CreditCardsDao {
	protected ConnectionManager connectionManager;

	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	public CreditCards create(CreditCards creditcard) throws SQLException {
		String createCreditCard =
			"INSERT INTO CreditCards(CardNumber,Expiration,UserName)" + 
					" VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(createCreditCard,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setLong(1, creditcard.getCardNumber());
			insertStmt.setDate(2, creditcard.getExpiration());
			insertStmt.setString(3, creditcard.getUserName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			return creditcard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
		public CreditCards delete(CreditCards creditcard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setLong(1, creditcard.getCardNumber());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	
	public CreditCards getCreditCardByCardNumber(long cardnumber) throws SQLException {
		String selectCreditCard = "SELECT * FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardnumber);
			results = selectStmt.executeQuery();
			if(results.next()) {
				long cardNumber = results.getLong("CardNumber");
				Date expiration = results.getDate("Expiration");
				String username = results.getString("UserName");

				CreditCards creditcard = new CreditCards(username, cardNumber, expiration);
				return creditcard;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
		}
	}
	
	public List<CreditCards> getCreditCardsByUserName(String userName) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards =
			"SELECT CardNumber,Expiration " +
			"FROM CreditCards " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				long cardNumber = results.getLong("CardNumber");
				Date expiration = results.getDate("Expiration");
				CreditCards creditcard = new CreditCards(userName, cardNumber, expiration);
				creditCards.add(creditcard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return creditCards;
	}
	
	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
		String updateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCreditCard);
			updateStmt.setDate(1, (java.sql.Date) newExpiration);
			updateStmt.setLong(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();

			creditCard.setExpiration(newExpiration);
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	

}