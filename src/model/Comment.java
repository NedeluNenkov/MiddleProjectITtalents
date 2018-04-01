package model;

import java.time.LocalDate;

public class Comment {

	private int id;
	private String text;
	private User user;
	
	public Comment(User user, int id, String text) {	
		this.setText(text);
		this.id = id;
		this.user = user;
	}
	
	public void setText(String text) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Invalid comment");
		}
		this.text = text;
	}
	
	public int getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
}
