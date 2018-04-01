package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class UserDAO {
	
	private User user;
	private static volatile UserDAO userDao;
	public static ArrayList<User> users;
	
	private UserDAO() {
		
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
	
	public void createFolder(String folderName) {
		if(folderName == null || folderName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name for folder");
		}
		user.folders.put(folderName, new TreeSet<>());
	}
	
	public void addPost(String folderName, String description) {
		//TODO path requires the path to the photo/video the User chooses to upload
		if(!user.getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		Post post = new Post("here we should write the path", user);
		post.addDescription(description);
		if(folderName == null) {
			user.folders.get("Untiteled").add(post);
		} else {
			user.folders.get(folderName).add(post);
		}
		user.posts.add(post);
	}
		
	public void writeComment(Post post) {
		if(!user.getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		// read Text
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		post.addComment(new Comment(user, text));
		sc.close();
	}
	
	public static User search(String username) {
		boolean hasUser = false;
		int userId = 0;
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getUsername().equals(username)) {
				hasUser = true;
				userId = i;
				break;
			}
		}
		if(hasUser) {
			return users.get(userId);
		} else {
			throw new IllegalArgumentException("User not found!");
		}
	}
	
	public static void login(String username, String password) {
		boolean exists = false;
		for (User us: users) {
			if((us.getUsername().equals(username) || us.getEmail().equals(username)) && us.getPassword().equals(password)) {
				exists = true;
				us.setIsLogged(true);
				break;
			}
		}
		if(!exists) {
			throw new IllegalArgumentException("Incorrect username/password!");
		}
	}
	
	public void likePost(Post post) {
		if(!user.getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		post.likePost(user);
	}
	
	public void dislikePost(Post post) {
		if(!user.getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		post.dislikePost(user);
	}
	
	public void logout() {
		user.setIsLogged(false);
	}
	
	public static void register(String username, String firstName, String lastName, String password, String email, String passwordCopy) {
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getEmail().equals(email) || users.get(i).getUsername().equals(username)) {
				throw new IllegalArgumentException("A user with this username or email already exists!");
			}
		}
		if(!password.equals(passwordCopy)) {
			throw new IllegalArgumentException("Passwords do not match!");
		}
		users.add(new User(username, firstName, lastName, password, email, passwordCopy));
	}
	
	public Set<Post> getPosts() {
		return Collections.unmodifiableSet(user.posts);
	}
}
