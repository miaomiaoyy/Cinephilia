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

public class FoodCartRestaurantsDao {
	protected ConnectionManager connectionManager;

	private static FoodCartRestaurantsDao instance = null;
	protected FoodCartRestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	public static FoodCartRestaurantsDao getInstance() {
		if(instance == null) {
			instance = new FoodCartRestaurantsDao();
		}
		return instance;
	}
	public FoodCartRestaurants create(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		RestaurantsDao restaurants = RestaurantsDao.getInstance();
		Restaurants restaurant = restaurants.create(foodCartRestaurant);
		foodCartRestaurant.setRestaurantId(restaurant.getRestaurantId());
		
		String createfoodCartRestaurant = 
			"INSERT INTO FoodCartRestaurant(RestaurantId,Licensed)" + 
					" VALUES(?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(createfoodCartRestaurant,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, foodCartRestaurant.getRestaurantId());
			insertStmt.setBoolean(2, foodCartRestaurant.isLicensed());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			return foodCartRestaurant;
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
	
	
	public FoodCartRestaurants delete(FoodCartRestaurants foodCartRestaurant) throws SQLException {
		String deleteFoodCartRestaurants = "DELETE FROM FoodCartRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFoodCartRestaurants);
			deleteStmt.setLong(1, foodCartRestaurant.getRestaurantId());
			deleteStmt.executeUpdate();
			
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			restaurantsDao.delete(foodCartRestaurant);
			
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
	public FoodCartRestaurants getFoodCartRestaurantById(int restaurantId) throws SQLException {
		String selectFoodCartRestaurant = "SELECT Licensed FROM FoodCartRestaurant WHERE RestaurantId=?;";
		
		RestaurantsDao restaurants = RestaurantsDao.getInstance();
		Restaurants restaurant = restaurants.getRestaurantById(restaurantId);
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurant);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				boolean licensed = results.getBoolean("Licensed");

				FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(restaurant, licensed);
				return foodCartRestaurant;
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
	
	public List<FoodCartRestaurants> getFoodCartRestaurantsByCompanyName(String companyName) throws SQLException {
		String selectFoodCartRestaurants = "SELECT * FROM FoodCartRestaurant INNER JOIN Restaurants "
				+ "ON Restaurants.RestaurantId = FoodCartRestaurant.RestaurantId "
				+ "WHERE Restaurants.CompanyName=?;";
		
		List<FoodCartRestaurants> foodCartRestaurants = new ArrayList<FoodCartRestaurants>();
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFoodCartRestaurants);
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
				boolean licensed = results.getBoolean("Licensed");
				FoodCartRestaurants foodCartRestaurant = new FoodCartRestaurants(name, description, menu, hours, street1, street2,
						city, state, companyName, zip, active, cuisineType, licensed);
				foodCartRestaurants.add(foodCartRestaurant);
			}
			return foodCartRestaurants;
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