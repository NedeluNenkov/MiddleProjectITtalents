package model;

import java.sql.SQLException;

public interface ICommentDao {

	
	public void addComment(Comment comment) throws SQLException;
	
	public void deleteComment(int id) throws SQLException;
	
	public void changeComment(Comment comment) throws SQLException;
}
