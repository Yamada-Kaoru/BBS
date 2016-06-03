package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.Branch;
import bulletin_board.exception.SQLRuntimeException;

public class BranchDao {

	public  List<Branch> getBranch(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql  = "SELECT * FROM branches ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Branch> toBranchList(ResultSet rs)
			throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name  = rs.getString("branch_name");

				Branch branch = new Branch();
				branch.setName(name);
				branch.setId(id);


				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}

