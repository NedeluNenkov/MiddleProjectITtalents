package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDAO implements ICommentDao {
	
	private static CommentDAO instance;
	private Connection connection;
	
	public static CommentDAO getInstance() {
		if(instance == null) {
			instance = new CommentDAO();
		}
		return instance;
	}
	
	private CommentDAO() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public void addComment(int id, String text) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (id, text) VALUES (?,?)");
		statement.setInt(1, id);
		statement.setString(2, text);
		statement.executeUpdate();
	}

	@Override
	public void deleteComment(int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE ? = id");
		statement.setInt(1, id);
		statement.executeUpdate();
	}
	
	@Override
	public void changeComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("UPDATE comments SET text = ? WHERE ? = id");
		statement.setString(1, comment.getText());
		statement.setInt(2, comment.getId());
		statement.executeUpdate();
	}
}
