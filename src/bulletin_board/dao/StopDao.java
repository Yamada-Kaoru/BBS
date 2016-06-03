package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bulletin_board.beans.User;
import bulletin_board.exception.SQLRuntimeException;

public class StopDao {

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" status = 0");
			sql.append(", update_date = CURRENT_TIMESTAMP");
			sql.append(" WHERE");
			sql.append(" users.id = ?");
			ps = (PreparedStatement) connection.prepareStatement(sql.toString());

			ps.setInt(1, user.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}