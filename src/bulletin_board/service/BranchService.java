package bulletin_board.service;

import static bulletin_board.Utils.CloseableUtil.*;
import static bulletin_board.Utils.DBUtil.*;

import java.util.List;

import com.mysql.jdbc.Connection;

import bulletin_board.beans.Branch;
import bulletin_board.dao.BranchDao;

public class BranchService {

	public  List<Branch> getBranch() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao BranchDao = new BranchDao();

			List<Branch> ret = BranchDao.getBranch(connection);

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

