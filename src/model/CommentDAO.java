package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
	public void addComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO comments (text) VALUES (?)"
				,Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, comment.getText());
		statement.executeUpdate();
		comment.setId(statement.getGeneratedKeys().getLong(1));
		statement.close();
	}

	@Override
	public void deleteComment(int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("DELETE FROM comments WHERE ? = id");
		statement.setInt(1, id);
		statement.executeUpdate();
		statement.close();
	}
	
	@Override
	public void changeComment(Comment comment) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("UPDATE comments SET text = ? WHERE id = ?");
		statement.setString(1, comment.getText());
		statement.setLong(2, comment.getId());
		statement.executeUpdate();
		statement.close();
	}
}
