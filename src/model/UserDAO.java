package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class UserDAO implements IUserDAO{
	
	private static volatile UserDAO userDao;
	private Connection connection;
	
	private UserDAO() {
		this.connection = DBManager.getInstance().getConnection();
	}
	
	public static UserDAO getInstance() {
		if(userDao == null) {
			synchronized (UserDAO.class) {
				if(userDao == null) {
					userDao = new UserDAO();
				}
			}
		}
		return userDao;
	}


	@Override
	public User getByID(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User u) throws Exception {
		// using PreparedStatement we escape SQLInjection !
				PreparedStatement s = connection.prepareStatement("INSERT INTO users (first_name, last_name, username, password, email) VALUES (?,?,?,?,?)");
				// we set the values in the order from the query
				s.setString(1, u.getFirstName());
				s.setString(2, u.getLastName());
				s.setString(3, u.getUsername());
				s.setString(4, u.getPassword());
				s.setString(5, u.getEmail());
				s.executeUpdate();
		
	}

	@Override
	public void changeUser(User u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<User> getAllPosts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(String username) throws Exception {
		return null;
	}

	@Override
	public boolean checkForUser(String username, String password) throws Exception {
		for (User u : getAllUsers()) {
			if (username == u.getUsername() && password == u.getPassword()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<User> getAllUsers() throws SQLException {
		PreparedStatement s = connection.prepareStatement("SELECT username FROM users");
		HashSet<User> users = new HashSet<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			User u = new User(	result.getInt("id"),
								result.getString("fristName"), 
								result.getString("lastName"),
								result.getString("username"), 
								result.getString("email"),
								result.getString("pasword"));
			users.add(u);
		}
		return users;
	}

}
