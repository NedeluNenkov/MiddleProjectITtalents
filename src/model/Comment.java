package model;

import java.time.LocalDate;

public class Comment implements Comparable<Comment>{

	private String text;
	private User user;
	private LocalDate date;
	
	public Comment(User user, String text) {	
		this.setText(text);
		this.user = user;
		this.date = LocalDate.now();
	}
	
	public void rewriteText(String comment) {
		this.setText(comment);
	}
	
	private void setText(String comment) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("Invalid comment");
		}
		this.text = comment;
	}

	@Override
	public int compareTo(Comment c) {
		if (this.date == c.date) {
			return 1;
		}
		return this.date.compareTo(c.date);
	}
}
