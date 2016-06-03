package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bulletin_board.beans.Comment;
import bulletin_board.exception.SQLRuntimeException;

public class CommentDao {

public void insert(Connection connection, Comment comment) {

	PreparedStatement ps = null;
	try {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO comments (");
		sql.append("user_id");
		sql.append(",article_Id");
		sql.append(", text");
		sql.append(", insert_date");
		sql.append(", update_date");
		sql.append(") VALUES(");
		sql.append("?");
		sql.append(", ?");
		sql.append(", ?");
		sql.append(", CURRENT_TIMESTAMP");
		sql.append(", CURRENT_TIMESTAMP");
		sql.append(")");

		ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setInt(1, comment.getUserId());
		ps.setInt(2, comment.getarticleId());
		ps.setString(3, comment.getCommentText());

		ps.executeUpdate();
	} catch (SQLException e) {

		throw new SQLRuntimeException(e);
	} finally {
		close(ps);
	}
}
}



