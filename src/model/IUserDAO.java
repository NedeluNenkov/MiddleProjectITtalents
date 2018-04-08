package model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public interface IUserDAO {

	User getByID(int id) throws Exception;
	User getByUsername(String username) throws Exception;
	void saveUser(User u) throws Exception;
	void changeUser(User u) throws SQLException;
	boolean checkForUser(String username, String password) throws Exception;
	ConcurrentHashMap<String, User> getAllUsers();
	Collection<User> getAllPosts() throws Exception;
	void getUsersFromDb() throws SQLException;
}
