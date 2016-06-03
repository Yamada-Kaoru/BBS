package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.UserArticle;
import bulletin_board.exception.SQLRuntimeException;

public class UserArticleDao {

	public List<UserArticle> getUserArticles(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_articles ");
			sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserArticle> ret = toUserArticleList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserArticle> toUserArticleList(ResultSet rs)
			throws SQLException {

		List<UserArticle> ret = new ArrayList<UserArticle>();
		try {
			while (rs.next()) {
				String title = rs.getString("title");
				String category = rs.getString("category");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				UserArticle article = new UserArticle();
				article.setTitle(title);
				article.setName(name);
				article.setId(id);
				article.setUserId(userId);
				article.setText(text);
				article.setCategory(category);
				article.setInsertDate(insertDate);


				ret.add(article);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
