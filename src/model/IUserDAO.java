package model;

import java.util.Collection;

public interface IUserDAO {

	User getByID(int id) throws Exception;
	User getByUsername(String username) throws Exception;
	void saveUser(User u) throws Exception;
	void changeUser(User u) throws Exception;
	boolean checkForUser(String username, String password) throws Exception;
	Collection<User> getAllUsers() throws Exception;
	Collection<User> getAllPosts() throws Exception;
}
