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

public class SitDownRestaurantsDao {
	protected ConnectionManager connectionManager;

	private static SitDownRestaurantsDao instance = null;
	protected SitDownRestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static SitDownRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new SitDownRestaurantsDao();
		}
		return instance;
	}
	public SitDownRestaurants create(SitDownRestaurants sitDownRestaurant)
	 throws SQLException {
		RestaurantsDao restaurants = RestaurantsDao.getInstance();
		Restaurants restaurant = restaurants.create(sitDownRestaurant);
		sitDownRestaurant.setRestaurantId(restaurant.getRestaurantId());
		
		String createSitDownRestaurant = 
		"INSERT INTO SitDownRestaurant(RestaurantId,Capacity)" + " VALUES(?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(createSitDownRestaurant,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, sitDownRestaurant.getRestaurantId());
			insertStmt.setInt(2, sitDownRestaurant.getCapacity());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			return sitDownRestaurant;
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
	
	public SitDownRestaurants getSitDownRestaurantById(int restaurantId) throws SQLException {
		String selectSitDownRestaurant = 
		"SELECT Capacity FROM SitDownRestaurant WHERE RestaurantId=?;";
		
		RestaurantsDao restaurants = RestaurantsDao.getInstance();
		Restaurants restaurant = restaurants.getRestaurantById(restaurantId);
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int capacity = results.getInt("Capacity");
				SitDownRestaurants sitDownRestaurant = new SitDownRestaurants(restaurant, capacity);
				return sitDownRestaurant;
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
	
	public List<SitDownRestaurants> getSitDownRestaurantsByCompanyName(String companyName) throws SQLException {
		String selectSitDownRestaurant = "SELECT * FROM SitDownRestaurant INNER JOIN Restaurants "
				+ "ON Restaurants.RestaurantId = SitDownRestaurant.RestaurantId "
				+ "WHERE Restaurants.CompanyName=?;";
		
		List<SitDownRestaurants> sitDownRestaurants = new ArrayList<SitDownRestaurants>();
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSitDownRestaurant);
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
				int capacity = results.getInt("Capacity");
				SitDownRestaurants sitDownRestaurant = new SitDownRestaurants(name, description, menu, hours, street1, street2,
						city, state, companyName, zip, active, cuisineType, capacity);
				sitDownRestaurants.add(sitDownRestaurant);
			}
			return sitDownRestaurants;
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
	

	public SitDownRestaurants delete(SitDownRestaurants sitDownRestaurants) throws SQLException {
		String deleteSitDownRestaurants = "DELETE FROM SitDownRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSitDownRestaurants);
			deleteStmt.setLong(1, sitDownRestaurants.getRestaurantId());
			deleteStmt.executeUpdate();
			
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			restaurantsDao.delete(sitDownRestaurants);
			
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

}