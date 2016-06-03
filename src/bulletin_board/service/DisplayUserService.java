package bulletin_board.service;

import static bulletin_board.Utils.CloseableUtil.*;
import static bulletin_board.Utils.DBUtil.*;

import java.util.List;

import com.mysql.jdbc.Connection;

import bulletin_board.beans.DisplayUser;
import bulletin_board.dao.DisplayUserDao;

public class DisplayUserService {

	public  List<DisplayUser> getDisplayUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			DisplayUserDao DisplayUserDao = new DisplayUserDao();

			List<DisplayUser> ret = DisplayUserDao.getUser(connection);

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




