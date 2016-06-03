package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.UserComment;
import bulletin_board.exception.SQLRuntimeException;

public class UserCommentDao {

	public List<UserComment> getComments(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comments ");
			sql.append("INNER JOIN users ON comments.user_id = users.id");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int articleId = rs.getInt("article_id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserComment comment = new UserComment();
				comment.setName(name);
				comment.setId(id);
				comment.setUserId(userId);
				comment.setArticleId(articleId);
				comment.setText(text);
				comment.setInsertDate(insertDate);


				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
