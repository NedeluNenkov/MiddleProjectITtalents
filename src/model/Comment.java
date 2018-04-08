package model;

import java.time.LocalDate;

public class Comment {

	private long id;
	private String text;
	private User user;
	
	public Comment(User user, String text) {
		this.user = user;
		setText(text);
	}
	
	public Comment(int id, String text) {
		this.id = id;
		setText(text);
	}
	
	public void setText(String text) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Invalid comment");
		}
		this.text = text;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
}
