package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bulletin_board.beans.Article;
import bulletin_board.exception.SQLRuntimeException;

public class ArticleDao {

public void insert(Connection connection, Article article) {

	PreparedStatement ps = null;
	try {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO articles (");
		sql.append("user_id");
		sql.append(", title");
		sql.append(", category");
		sql.append(", text");
		sql.append(", insert_date");
		sql.append(", update_date");
		sql.append(") VALUES(");
		sql.append("?");
		sql.append(", ?");
		sql.append(", ?");
		sql.append(", ?");
		sql.append(", CURRENT_TIMESTAMP");
		sql.append(", CURRENT_TIMESTAMP");
		sql.append(")");

		ps = (PreparedStatement) connection.prepareStatement(sql.toString());
		ps.setInt(1, article.getUserId());
		ps.setString(2, article.getTitle());
		ps.setString(3, article.getCategory());
		ps.setString(4, article.getText());

		ps.executeUpdate();
	} catch (SQLException e) {
		throw new SQLRuntimeException(e);
	} finally {
		close(ps);
	}
}
}
