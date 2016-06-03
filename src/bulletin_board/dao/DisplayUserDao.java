package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.DisplayUser;
import bulletin_board.exception.SQLRuntimeException;

public class DisplayUserDao {

	public  List<DisplayUser> getUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT users.id, account, status,  users.name, branch_name, possition_name, password, insert_date FROM users, branches, possitions");
			sql.append(" WHERE users.branch_id = branches.id ");
			sql.append(" AND users.possition_id = possitions.id;");


			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<DisplayUser> ret = toUsersList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<DisplayUser> toUsersList(ResultSet rs)
			throws SQLException {

		List<DisplayUser> ret = new ArrayList<DisplayUser>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account  = rs.getString("account");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String branch_name = rs.getString("branch_name");
				String possition_name = rs.getString("possition_name");
				String status = rs.getString("status");
				Timestamp insertDate = rs.getTimestamp("insert_date");

				DisplayUser displayUser = new DisplayUser();
				displayUser.setAccount(account);
				displayUser.setName(name);
				displayUser.setId(id);
				displayUser.setStatus(status);
				displayUser.setPassword(password);
				displayUser.setBranchName(branch_name);
				displayUser.setPossitionName(possition_name);
				displayUser.setInsertDate(insertDate);


				ret.add(displayUser);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}

