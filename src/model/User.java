package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$",
			Pattern.CASE_INSENSITIVE);
	private static final int PASSWORD_MIN_LENGTH = 5;
	private static final int PASSWORD_MAX_LENGTH = 30;
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	TreeSet<Post> posts;
	TreeMap<String, TreeSet<Post>> folders;

	public User(String username, String firstName, String lastName, String password, String email) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setPassword(password);
		setEmail(email);
		this.posts = new TreeSet<>();
		this.folders = new TreeMap<>();
		this.folders.put("Untiteled", new TreeSet<>());
	}
	
	public User(int id, String username, String firstName, String lastName, String password, String email) {
		this(username, firstName, lastName, password, email);
		this.id = id;
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

	private void setEmail(String email) {
		if(validate(email)) {
			this.email = email;
		}
		this.email = email;
	}

	private void setPassword(String password) {
//		if (password == null || password.isEmpty() || password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH || !password.equals(passwordCopy)) {
//			throw new IllegalArgumentException("Invalid input");
//		}
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
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

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Set<Post> getPosts() {
		return Collections.unmodifiableSet(this.posts);
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
	
	
	private static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.matches();
	}
}
