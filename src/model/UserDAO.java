package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import java.sql.Statement;

public class UserDAO implements IUserDAO{
	
	private static volatile UserDAO userDao;
	private Connection connection;
	//username -> user
	private static ConcurrentHashMap<String, User> allUsers = new ConcurrentHashMap<>();
	
	private UserDAO() {
		this.connection = DBManager.getInstance().getConnection();
	}
	
	public static UserDAO getInstance() throws SQLException {
		if(userDao == null) {
			synchronized (UserDAO.class) {
				if(userDao == null) {
					userDao = new UserDAO();
					userDao.getUsersFromDb();
				}
			}
		}
		return userDao;
	}
	
	//if needed use to get all the users from the db to the collection
	public void getUsersFromDb() throws SQLException {
		PreparedStatement s = connection.prepareStatement("SELECT * FROM users");
		ResultSet result = s.executeQuery();
		while(result.next()) {
			User u = new User(	result.getLong("id"),
								result.getString("username"), 
								result.getString("first_name"), 
								result.getString("last_name"),
								result.getString("email"),
								result.getString("password"));
			allUsers.put(result.getString("username"), u);
		}
	}


	@Override
	public User getByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User u) throws SQLException {
		// using PreparedStatement we escape SQLInjection !
				PreparedStatement s = connection.prepareStatement("INSERT INTO users (first_name, last_name, username, password, email) VALUES (?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				// we set the values in the order from the query
				s.setString(1, u.getFirstName());
				s.setString(2, u.getLastName());
				s.setString(3, u.getUsername());
				s.setString(4, u.getPassword());
				s.setString(5, u.getEmail());
				s.executeUpdate();
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				u.setId(rs.getLong(1));
				s.close();
				//putting in the collection with all registered users
				allUsers.put(u.getUsername(), u);
		
	}

	@Override
	public void changeUser(User u) throws SQLException {
		PreparedStatement ps = 
				connection.
				prepareStatement("UPDATE users WHERE SET  first_name = ?, last_name = ?, password = ?, email = ? WHERE id = ?");
				ps.setString(1, u.getFirstName());
				ps.setString(2, u.getLastName());
				ps.setString(3, u.getPassword());
				ps.setString(4, u.getEmail());
				ps.setLong(5, u.getId());
				ps.executeUpdate();
				ps.close();
			
				allUsers.put(u.getUsername(), u);
		
	}

	@Override
	public Collection<User> getAllPosts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(String username) throws SQLException {
		if(allUsers.containsKey(username)) {
			return allUsers.get(username);
		} else {
			throw new IllegalArgumentException("No such user exists!");
		}
		
	}

	@Override
	public boolean checkForUser(String username, String password) {
		for (User u : getAllUsers().values()) {
			if (username == u.getUsername() && password == u.getPassword()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ConcurrentHashMap<String, User> getAllUsers() {
		return allUsers;
	}

}
