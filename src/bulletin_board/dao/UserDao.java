package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bulletin_board.beans.User;
import bulletin_board.exception.SQLRuntimeException;

public class UserDao {

	public User getUser(Connection connection, String account,
			String password) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE account = ? AND password = ?";

			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, account );
			ps.setString(2, password);


			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size())  {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try{
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String branch_id = rs.getString("branch_id");
				String possition_id = rs.getString("possition_id");
				String status = rs.getString("status");
				Timestamp insertDate = rs.getTimestamp("insert_date");
				Timestamp updateDate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setPassword(password);
				user.setBranchId(branch_id);
				user.setPossitionId(possition_id);
				user.setStatus(status);
				user.setInsertDate(insertDate);
				user.setUpdateDate(updateDate);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users (");
			sql.append("account");
			sql.append(", name");
			sql.append(", password");
			sql.append(", branch_id");
			sql.append(", possition_id");
			sql.append(", status");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES(");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = (PreparedStatement) connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getPossitionId());
			ps.setString(6, user.getStatus());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void updatePassword(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" name = ?");
			sql.append(", account = ?");
			sql.append(", password = ?");
			sql.append(", branch_id = ?");
			sql.append(", possition_id = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");
			sql.append(" WHERE");
			sql.append(" users.id = ?");



			ps = (PreparedStatement) connection.prepareStatement(sql.toString());


			ps.setString(1, user.getName());
			ps.setString(2, user.getAccount());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getBranchId());
			ps.setString(5, user.getPossitionId());
			ps.setInt(6, user.getId());
			System.out.println(ps);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append(" name = ?");
			sql.append(", account = ?");
			sql.append(", branch_id = ?");
			sql.append(", possition_id = ?");
			sql.append(", update_date = CURRENT_TIMESTAMP");
			sql.append(" WHERE");
			sql.append(" users.id = ?");



			ps = (PreparedStatement) connection.prepareStatement(sql.toString());


			ps.setString(1, user.getName());
			ps.setString(2, user.getAccount());
			ps.setString(3, user.getBranchId());
			ps.setString(4, user.getPossitionId());
			ps.setInt(5, user.getId());
			System.out.println(ps);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users ");
			sql.append(" WHERE users.id = ?");

			ps = (PreparedStatement) connection.prepareStatement(sql.toString());
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
