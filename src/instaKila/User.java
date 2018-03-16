package instaKila;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$",
			Pattern.CASE_INSENSITIVE);
	private static final int PASSWORD_MIN_LENGTH = 5;
	private static final int PASSWORD_MAX_LENGTH = 30;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private TreeSet<Post> posts;
	private TreeMap<String, TreeSet<Post>> folders;
	private boolean isLogged = false; 
	private static ArrayList<User> users;
	
	static {
		users = new ArrayList<>();
		users.add(new User("Ivancho", "Ivan", "Ivanov", "12345", "ivan@abv.bg", "12345"));
	}

	public User(String username, String firstName, String lastName, String password, String email, String passwordCopy) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password, passwordCopy);
		setEmail(email);
		this.posts = new TreeSet<>();
		this.folders = new TreeMap<>();
		this.folders.put("Untiteled", new TreeSet<>());
	}

	private void setLastName(String lastName) {
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("Invalid input");
		}
		this.lastName = lastName;
	}

	private void setFirstName(String firstName) {
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("Invalid input");
		}
		this.firstName = firstName;
	}

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.matches();
	}

	private void setEmail(String email) {
		if(validate(email)) {
			this.email = email;
		}
		this.email = email;
	}

	private void setPassword(String password, String passwordCopy) {
		if (password == null || password.isEmpty() || password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH || !password.equals(passwordCopy)) {
			throw new IllegalArgumentException("Invalid input");
		}
		this.password = password;
	}

	private void setUsername(String username) {
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("Invalid input");
		}
		for (int i = 0; i < username.length(); i++) {
			if (!Character.isAlphabetic(username.charAt(i))) {
				throw new IllegalArgumentException("Invalid input");
			}
		}
		this.username = username;
	}

	public void createFolder(String folderName) {
		if(folderName == null || folderName.isEmpty()) {
			throw new IllegalArgumentException("Invalid name for folder");
		}
		this.folders.put(folderName, new TreeSet<>());
	}
	
	public void addPost(String folderName, String description) {
		//TODO path requires the path to the photo/video the User chooses to upload
		if(!getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		Post post = new Post("here we should write the path", this);
		post.addDescription(description);
		if(folderName == null) {
			this.folders.get("Untiteled").add(post);
		} else {
			this.folders.get(folderName).add(post);
		}
		this.posts.add(post);
	}
	
	public void likePost(Post post) {
		if(!getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		post.likePost(this);
	}
	
	public void dislikePost(Post post) {
		if(!getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		post.dislikePost(this);
	}
	
	public void writeComment(Post post) {
		if(!getIsLogged()) {
			throw new IllegalAccessError("You must be logged in!");
		}
		// read Text
		Scanner sc = new Scanner(System.in);
		String text = sc.nextLine();
		post.addComment(new Comment(this, text));
		sc.close();
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	private String getUsername() {
		return this.username;
	}
	
	public static void login(String username, String password) {
		for (User us: users) {
			if((us.getUsername().equals(username) || us.getEmail().equals(username)) && us.getPassword().equals(password)) {
				us.setIsLogged(true);
			}
		}
	}
	
	private void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public boolean getIsLogged() {
		return this.isLogged;
	}

	public void logout() {
		
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
}
