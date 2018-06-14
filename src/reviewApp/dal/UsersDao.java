package reviewApp.dal;

import reviewApp.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UsersDao {
	protected ConnectionManager connectionManager;

	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	public Users create(Users User) throws SQLException {
		String createUser =
				"INSERT INTO Users(UserName,Password,FirstName,LastName,Email,Phone)" + " VALUES(?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(createUser,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, User.getUserName());
			insertStmt.setString(2, User.getPassword());
			insertStmt.setString(3, User.getFirstName());
			insertStmt.setString(4, User.getLastName());
			insertStmt.setString(5, User.getEmail());
			insertStmt.setString(6, User.getPhone());
			/*insertStmt.setTimestamp(2, new Timestamp(blogComment.getCreated().getTime()));
			insertStmt.setString(3, blogComment.getBlogUser().getUserName());
			insertStmt.setInt(4, blogComment.getBlogPost().getPostId());*/
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			/*int commentId = -1;
			if(resultKey.next()) {
				commentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			blogComment.setCommentId(commentId);*/
			return User;
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
	
	public Users delete(Users User) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, User.getUserName());
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
	
	public Users getUserByUserName(String UserName) throws SQLException {
		String getUser = "SELECT * FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getUser);
			selectStmt.setString(1, UserName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String username = results.getString("UserName");
				String firstname = results.getString("FirstName");
				String lastname = results.getString("LastName");
				String password = results.getString("Password");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				Users User = new Users(username, password, firstname, lastname,
						email, phone);
				return User;
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
	

}