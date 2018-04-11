package controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import model.Comment;
import model.Post;
import model.User;
import model.UserDAO;

public class UserManager {

	private static volatile UserManager instance;
	private HashSet<User> loggedUsers;
	
	private UserManager() {
		this.loggedUsers = new HashSet<>();
	}

	public static UserManager getInstance() {
		if (instance == null) {
			synchronized (UserManager.class) {
				if (instance == null) {
					instance = new UserManager();
				}
			}
		}
		return instance;
	}

	public boolean login(String username, String password) {
		boolean exists;
		try {
			exists = UserDAO.getInstance().checkForUser(username, password);
			if (!exists) {
				return false;
			} else {
				this.loggedUsers.add(UserDAO.getInstance().getByUsername(username));
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public void logout(User u) {
		this.loggedUsers.remove(u);
	}

	public boolean register(String username, String firstName, String lastName, String password, String email,
			String passwordCopy) {
		if (!password.equals(passwordCopy)) {
			return false;
		}
		try {
			for (User user : UserDAO.getInstance().getAllUsers().values()) {
				if (user.getUsername() == username || user.getEmail() == email) {
					return false;
				}
			}
			User u = new User(username, firstName, lastName, passwordCopy, email);
			UserDAO.getInstance().saveUser(u);
			return true;
		} catch (SQLException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
//	public void createFolder(String folderName) {
//		if(folderName == null || folderName.isEmpty()) {
//			throw new IllegalArgumentException("Invalid name for folder");
//		}
//		user.folders.put(folderName, new TreeSet<>());
//	}
	
//	public void addPost(String folderName, String description) {
//		//TODO path requires the path to the photo/video the User chooses to upload
//		if(!user.getIsLogged()) {
//			throw new IllegalAccessError("You must be logged in!");
//		}
//		Post post = new Post("here we should write the path", user);
//		post.addDescription(description);
//		if(folderName == null) {
//			user.folders.get("Untiteled").add(post);
//		} else {
//			user.folders.get(folderName).add(post);
//		}
//		user.posts.add(post);
//	}
//		
//	public void writeComment(Post post) {
//		if(this.loggedUsers.contains(arg0)) {
//			throw new IllegalAccessError("You must be logged in!");
//		}
//		// read Text
//		Scanner sc = new Scanner(System.in);
//		String text = sc.nextLine();
//		post.addComment(new Comment(user, text));
//		sc.close();
//	}
	
	public User search(String username) {
		UserDAO udao = null;
		try {
			udao = UserDAO.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(udao.getAllUsers().containsKey(username)) {
			return udao.getAllUsers().get(username);
		} else {
			throw new IllegalArgumentException("User not found!");
		}
	}
	
//	public void likePost(int id, Post post) {
//		if(id == 0) {
//			throw new IllegalAccessError("You must be logged in!");
//		}
//		post.likePost(user);
//	}
//	
//	public void dislikePost(int id, Post post) {
//		if(id == 0) {
//			throw new IllegalAccessError("You must be logged in!");
//		}
//		post.dislikePost(user);
//	}
}
