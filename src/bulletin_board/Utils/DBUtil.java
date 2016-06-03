package bulletin_board.Utils;


import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import bulletin_board.exception.SQLRuntimeException;

/**
 *DB関係のユーティリティ
 */
public class DBUtil {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/Bulletin-board";
	private static final String USER = "root";
	private static final String PASSWORD = "kaoruyamada";

	static {

		try{
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 *コネクション取得
	 *
	 *@return
	 * @throws SQLRuntimeException
	 */
	public static Connection getConnection() {

		try {
			Connection connection = (Connection) DriverManager.getConnection(URL, USER,
					PASSWORD);

			connection.setAutoCommit(false);

			return connection;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 *コミット
	 *
	 *@param connection
	 * @throws SQLRuntimeException
	 */
	public static void commit(Connection connection) {

		try{
			connection.commit();
		}catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 *ロールバック
	 *
	 *@param connection
	 * @throws SQLRuntimeException
	 */
	public static void rollback(Connection connection) {

		try{
			connection.rollback();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}

