package reviewApp.dal;

import reviewApp.model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * created by Yang on 2017/11/02
 */

public class ReservationsDao {
	protected ConnectionManager connectionManager;

	private static ReservationsDao instance = null;
	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationsDao getInstance() {
		if(instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}
	public Reservations create(Reservations reservation) throws SQLException {
		String createReservations =
			"INSERT INTO Reservations(Size,RestaurantId, UserName, Start, End) " + 
			" VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(createReservations,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, reservation.getSize());
			insertStmt.setInt(2, reservation.getRestaurantId());
			insertStmt.setString(3, reservation.getUserName());
			insertStmt.setDate(4, (Date) reservation.getStart());
			insertStmt.setDate(5, (Date) reservation.getEnd());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			
			int reservationId = -1;
			if(resultKey.next()) {
				reservationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reservation.setReservationId(reservationId);
			
			return reservation;
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
	
		
	public Reservations delete(Reservations reservation) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, reservation.getReservationId());
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


	public Reservations getReservationById(int reservationId) throws SQLException {
		String selectReservation = "SELECT * FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservation);
			selectStmt.setInt(1, reservationId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String userName = results.getString("UserName");
				int restaurantId = results.getInt("RestaurantId");
				Date start = results.getDate("Start");
				Date end = results.getDate("End");
				int size = results.getInt("Size");

				Reservations reservation = new Reservations(reservationId, 
						size,restaurantId, userName, start, end);
				return reservation;
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
	
	public List<Reservations> getReservationsByUserName(String userName) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservations =
			"SELECT * " +
			"FROM Reservations " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				Date start = results.getDate("Start");
				Date end = results.getDate("End");
				int size = results.getInt("Size");
				int reservationId = results.getInt("ReservationId");


				Reservations reservation = new Reservations(reservationId, 
						size,restaurantId, userName, start, end);
				reservations.add(reservation);
			}
			return reservations;
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
	}
	
	public List<Reservations> getReservationsByRestaurantId(int restaurantId) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservations =
			"SELECT * " +
			"FROM Reservations " +
			"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				Date start = results.getDate("Start");
				Date end = results.getDate("End");
				int size = results.getInt("Size");
				int reservationId = results.getInt("ReservationId");


				Reservations reservation = new Reservations(reservationId, 
						size,restaurantId, userName, start, end);
				reservations.add(reservation);
			}
			return reservations;
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
	}
	

}