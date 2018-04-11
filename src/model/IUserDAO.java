package model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public interface IUserDAO {

	User getByID(int id) throws SQLException;
	User getByUsername(String username) throws SQLException;
	void saveUser(User u) throws SQLException;
	void changeUser(User u) throws SQLException;
	boolean checkForUser(String username, String password);
	ConcurrentHashMap<String, User> getAllUsers();
	Collection<User> getAllPosts() throws SQLException;
	void getUsersFromDb() throws SQLException;
}
