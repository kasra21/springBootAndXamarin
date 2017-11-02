package com.boot.demo.services;

import org.springframework.stereotype.Service;

import com.boot.demo.model.AddUpdateUserCriteria;
import com.boot.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserService {
	
	// Returns specified user(s)
	public List<User> findByUserNameOrEmail(String username) {

		List<User> users = new ArrayList<>();
		Connection conn = getMySqlConnection();
		java.sql.Statement stmt = null;
		String query = "SELECT * FROM test_schema.test_table WHERE `user_name`=?";

		try {			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, username);		    
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString("USER_NAME");
				String email = rs.getString("EMAIL");
				String first = rs.getString("FIRST_NAME");
				String last = rs.getString("LAST_NAME");
				User userObj = new User(userName, email, first, last);
				users.add(userObj);
			}
		} catch (SQLException e) {
			// users will be set to empty
			users.clear();
		}
		
		return users;
	}

	// Add user(s) to db and returns added user(s)
	public List<User> addUser(AddUpdateUserCriteria add) {
		
		List<User> result = new ArrayList<>();
		Connection conn = getMySqlConnection();
		java.sql.Statement stmt = null;
		
		String first = add.getFirst() == null ? "" : add.getFirst();
		String last = add.getLast() == null ? "" : add.getLast();
		String userName = add.getUsername() == null ? "" : add.getUsername();
		String email = add.getEmail() == null ? "" : add.getEmail();
		
		String query = "INSERT INTO `test_schema`.`test_table` (`first_name`, `last_name`, `user_name`, `email`) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString (1, first);
			preparedStmt.setString (2, last);
		    preparedStmt.setString (3, userName);
		    preparedStmt.setString (4, email);
		    preparedStmt.execute();
		    conn.close();
			User u = new User(userName, email, first, last);
			result.add(u);
		} catch (SQLException e) {
			// users will be set to empty
			result.clear();
		}
		
		return result;
	}
	
	// Update user(s) to db and returns updated user(s)
	public List<User> updateUser(AddUpdateUserCriteria update) {
		
		List<User> result = new ArrayList<>();
		Connection conn = getMySqlConnection();
		
		String first = update.getFirst() == null ? "" : update.getFirst();
		String last = update.getLast() == null ? "" : update.getLast();
		String userName = update.getUsername() == null ? "" : update.getUsername();
		String email = update.getEmail() == null ? "" : update.getEmail();
		
		String query = "UPDATE `test_schema`.`test_table` SET `first_name`=?, `last_name`=?, `email`=?  WHERE `user_name`=?";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, first);
			preparedStmt.setString (2, last);
		    preparedStmt.setString (3, email);
		    preparedStmt.setString (4, userName);
		    preparedStmt.executeUpdate();
		    conn.close();
			User u = new User(userName, email, first, last);
			result.add(u);
		} catch (SQLException e) {
			// users will be set to empty
			result.clear();
		}
		
		return result;
	}
	
	// Deletes user(s) from db and returns deleted user(s)
	public List<User> deleteUser(String username) {
		
		List<User> users = new ArrayList<>();
		Connection conn = getMySqlConnection();		
		
		String query = "DELETE FROM `test_schema`.`test_table` WHERE `user_name`=?";
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, username);
		    preparedStmt.execute();
		    conn.close();
		    User userObj = new User(username, "Unknown", "Unknown", "Unknown");
			users.add(userObj);
		} catch (SQLException e) {
			// users will be set to empty
			users.clear();
		}

		return users;
	}

	// Returns all users
	public List<User> getAllUsers() {
		
		List<User> users = new ArrayList<>();
		Connection conn = getMySqlConnection();
		java.sql.Statement stmt = null;
		String query = "SELECT * FROM test_schema.test_table";

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String userName = rs.getString("USER_NAME");
				String email = rs.getString("EMAIL");
				String first = rs.getString("FIRST_NAME");
				String last = rs.getString("LAST_NAME");
				User userObj = new User(userName, email, first, last);
				users.add(userObj);
			}
		} catch (SQLException e) {
			// users will be set to empty
			users.clear();
		}
		
		return users;
	}

	// Test mysql database connection
	public String mysqlConnectTest() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return "Driver not found";
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/test_schema", "admin", "password");

		} catch (SQLException e) {
			return "Connection Failed";
		}

		if (connection != null) {
			return "Connection Successful";
		} else {
			return "Connection Failed";
		}
	}

	private Connection getMySqlConnection() {
		
		String jdbcUrl = "jdbc:mysql://192.168.56.1:3306/test_schema";
		String user = "admin";
		String password = "password";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			return null;
		}

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(jdbcUrl, user, password);
		} catch (SQLException e) {
			return null;
		}

		return connection;
	}

}
