package controller;

import java.sql.SQLException;

import model.Comment;
import model.CommentDAO;

public class CommentManager {
	
	private static CommentManager instance;

	public static synchronized CommentManager getInstance() {
		if(instance == null) {
			instance = new CommentManager();
		}
		return instance;
	}
	
	public void changeComment(int id, String text) {
		Comment comment = new Comment(id, text);
		try {
			CommentDAO.getInstance().changeComment(comment);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createComment(int id, String text) {
		if(text == null || text.isEmpty()) {
			throw new IllegalAccessError("Invalid text");
		}
		try {
			CommentDAO.getInstance().addComment(id, text);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void deleteComment(int id) {
		try {
			CommentDAO.getInstance().deleteComment(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

