package bulletin_board.dao;

import static bulletin_board.Utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletin_board.beans.Possition;
import bulletin_board.exception.SQLRuntimeException;

public class PossitionDao {

	public  List<Possition> getPossition(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql  = "SELECT * FROM possitions ";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Possition> ret = toPossitionList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Possition> toPossitionList(ResultSet rs)
			throws SQLException {

		List<Possition> ret = new ArrayList<Possition>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name  = rs.getString("possition_name");

				Possition possition = new Possition();
				possition.setName(name);
				possition.setId(id);


				ret.add(possition);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}