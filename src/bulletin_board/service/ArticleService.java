package bulletin_board.service;

import static bulletin_board.Utils.CloseableUtil.*;
import static bulletin_board.Utils.DBUtil.*;

import java.util.List;

import com.mysql.jdbc.Connection;

import bulletin_board.beans.Article;
import bulletin_board.beans.UserArticle;
import bulletin_board.dao.ArticleDao;
import bulletin_board.dao.UserArticleDao;


public class ArticleService {

	public void register(Article article) {

		Connection connection = null;
		try {
			connection = getConnection();

			ArticleDao articleDao = new ArticleDao();
			articleDao.insert(connection, article);

			commit(connection);
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
	private static final int LIMIT_NUM = 1000;

	public List<UserArticle> getArticle() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserArticleDao articleDao = new UserArticleDao();
			List<UserArticle> ret = articleDao.getUserArticles(connection, LIMIT_NUM);

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

