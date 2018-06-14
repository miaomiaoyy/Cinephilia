package reviewApp.dal;

import reviewApp.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Yang on 2017/11/02
 */

public class TakeOutRestaurantsDao {
	protected ConnectionManager connectionManager;

	private static TakeOutRestaurantsDao instance = null;
	protected TakeOutRestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static TakeOutRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantsDao();
		}
		return instance;
	}
	public TakeOutRestaurants create(TakeOutRestaurants takeOutRestaurant) throws SQLException {
		RestaurantsDao restaurants = RestaurantsDao.getInstance();
		Restaurants restaurant = restaurants.create(takeOutRestaurant);
		takeOutRestaurant.setRestaurantId(restaurant.getRestaurantId());
		String createTakeOutRestaurant = 
		"INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime)" + " VALUES(?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(createTakeOutRestaurant,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, takeOutRestaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			return takeOutRestaurant;
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
	
	public TakeOutRestaurants delete(TakeOutRestaurants takeOutRestaurant) throws SQLException {
		String deleteTakeOutRestaurants = "DELETE FROM TakeOutRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTakeOutRestaurants);
			deleteStmt.setLong(1, takeOutRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();
			
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			restaurantsDao.delete(takeOutRestaurant);
			
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
	public TakeOutRestaurants getTakeOutRestaurantById(int restaurantId) throws SQLException {
		String selectTakeOutRestaurant = 
		"SELECT MaxWaitTime FROM TakeOutRestaurant WHERE RestaurantId=?;";
		RestaurantsDao restaurants = RestaurantsDao.getInstance();
		Restaurants restaurant = restaurants.getRestaurantById(restaurantId);
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurant);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int maxWaitTime = results.getInt("MaxWaitTime");

				TakeOutRestaurants takeOutRestaurant = new TakeOutRestaurants(restaurant, maxWaitTime);
				return takeOutRestaurant;
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
	
	public List<TakeOutRestaurants> getTakeOutRestaurantsByCompanyName(String companyName) throws SQLException {
		String selectTakeOutRestaurants = "SELECT * FROM TakeOutRestaurant INNER JOIN Restaurants "
				+ "ON Restaurants.RestaurantId = TakeOutRestaurant.RestaurantId "
				+ "WHERE Restaurants.CompanyName=?;";
		
		List<TakeOutRestaurants> takeOutRestaurants = new ArrayList<TakeOutRestaurants>();
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTakeOutRestaurants);
			selectStmt.setString(1, companyName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String name = results.getString("Name");
				String description = results.getString("Description");
				String menu = results.getString("Menu");
				String hours = results.getString("Hours");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				boolean active = results.getBoolean("Active");
				Restaurants.Type cuisineType = Restaurants.Type.valueOf(results.getString("CuisineType"));
				int maxWaitTime = results.getInt("MaxWaitTime");
				TakeOutRestaurants takeOutRestaurant = new TakeOutRestaurants(name, description, menu, hours, street1, street2,
						city, state, companyName, zip, active, cuisineType, maxWaitTime);
				takeOutRestaurants.add(takeOutRestaurant);
			}
			return takeOutRestaurants;
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

}