package bulletin_board.service;

import static bulletin_board.Utils.CloseableUtil.*;
import static bulletin_board.Utils.DBUtil.*;

import com.mysql.jdbc.Connection;

import bulletin_board.Utils.CipherUtil;
import bulletin_board.beans.User;
import bulletin_board.dao.UserDao;

public class LoginService {

	public User login(String account, String password) {

		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getUser(connection, account, encPassword);
			commit(connection);
			return user;

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
