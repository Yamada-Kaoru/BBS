package bulletin_board.service;

import static bulletin_board.Utils.CloseableUtil.*;
import static bulletin_board.Utils.DBUtil.*;

import java.util.List;

import com.mysql.jdbc.Connection;

import bulletin_board.beans.UserArticle;
import bulletin_board.dao.SearchArticleDao;


public class SearchArticleService {


	public List<UserArticle> getSearchArticle(String firstday,String lastday,String searchCategory ) {

		Connection connection = null;
		try {
			connection = getConnection();

			SearchArticleDao searchArticleDao = new SearchArticleDao();

			List<UserArticle> ret = searchArticleDao.getSearchUserArticles(connection,  firstday, lastday, searchCategory);

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


