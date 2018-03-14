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
	private boolean isLogged = false; // moje da ni trqbva ama ne go polzvame za sega!!!
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

	public void writeComment(Post post) {
		// read Text
		String text = new Scanner(System.in).nextLine();
		//TODO
		//post.addComment(new Comment(this, text));
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
	
	public static boolean login(String username, String password) {
		for (User us: users) {
			if((us.getUsername().equals(username) || us.getEmail().equals(username)) && us.getPassword().equals(password)) {
				return true;
			}
		}
		return false;	
	}

	public static void register(String username, String firstName, String lastName, String password, String email, String passwordCopy) {
		users.add(new User(username, firstName, lastName, password, email, passwordCopy));
	}
}
