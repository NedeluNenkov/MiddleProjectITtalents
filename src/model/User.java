package model;

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
	public TreeSet<Post> posts;
	public TreeMap<String, TreeSet<Post>> folders;
	private boolean isLogged = false; 
	

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

	private static boolean validate(String emailStr) {
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
		String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		this.password = generatedSecuredPasswordHash;
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
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	
	public boolean getIsLogged() {
		return this.isLogged;
	}

}
