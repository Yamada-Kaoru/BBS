package bulletin_board.service;

import static bulletin_board.Utils.CloseableUtil.*;
import static bulletin_board.Utils.DBUtil.*;

import java.util.List;

import com.mysql.jdbc.Connection;

import bulletin_board.beans.Possition;
import bulletin_board.dao.PossitionDao;

public class PossitionService {

	public  List<Possition> getPossition() {

		Connection connection = null;
		try {
			connection = getConnection();

			PossitionDao PossitionDao = new PossitionDao();

			List<Possition> ret = PossitionDao.getPossition(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}