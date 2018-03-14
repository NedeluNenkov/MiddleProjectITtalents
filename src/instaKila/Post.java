package instaKila;

import java.util.TreeSet;

public class Post {

	private final String path;
	private String description;
	private int likes;
	private int dislikes;
	private User user;
	private TreeSet<Comment> comments;
	
	public Post(String path, User user) {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("Invalid input post");
		}
		this.path = path;
		this.user = user;
		this.comments = new TreeSet<>();
	}
	
	public void addDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.description = description;
	}
	
	public void likePost() {
		this.likes++;
	}
	
	public void dislikePost() {
		this.dislikes++;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
}
