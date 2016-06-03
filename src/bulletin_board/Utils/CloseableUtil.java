package bulletin_board.Utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bulletin_board.exception.IORuntimeException;
import bulletin_board.exception.SQLRuntimeException;

public class CloseableUtil {

	public static void close(Closeable closeable) throws IORuntimeException {

		if (closeable == null) {
			return;
		}

		try {
			closeable.close();
		} catch (IOException e) {
			throw new IORuntimeException();
		}
	}

	public static void close(Connection connection) throws SQLRuntimeException {
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void close(Statement statement) throws SQLRuntimeException {
		if (statement == null) {
			return;
		}
		try {
			statement.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void close(ResultSet rs) throws SQLRuntimeException {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}

