package model;

import java.util.TreeSet;

public class Post {

	private final String path;
	private String description;
	private int likes;
	private int dislikes;
	private User user;
	private TreeSet<Comment> comments;
	private TreeSet<User> listOfLikes;
	private TreeSet<User> listOfDislikes;
	
	public Post(String path, User user) {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("Invalid input post");
		}
		this.path = path;
		this.user = user;
		this.comments = new TreeSet<>();
		this.listOfLikes = new TreeSet<>();
		this.listOfDislikes = new TreeSet<>();
	}
	
	public void addDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.description = description;
	}
	
	public void likePost(User user) {
		if (this.listOfLikes.contains(user)) {
			return;
		}
		if (this.listOfDislikes.contains(user)) {
			this.listOfDislikes.remove(user);
			this.dislikes--;
		}
		this.likes++;
		this.listOfLikes.add(user);
	}
	
	public void dislikePost(User user) {
		if (this.listOfDislikes.contains(user)) {
			return;
		}
		if (this.listOfLikes.contains(user)) {
			this.listOfLikes.remove(user);
			this.likes--;
		}
		this.dislikes++;
		this.listOfDislikes.add(user);
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
}
