package bulletin_board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.Article;
import bulletin_board.beans.User;
import bulletin_board.service.ArticleService;


@WebServlet(urlPatterns = {"/articleup"})
public class ArticleUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
	IOException {

		request.getRequestDispatcher("articleup.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
	IOException {
		HttpSession session = request.getSession();

		List<String> articles = new ArrayList<String>();

		if (isValid(request, articles) == true) {

			User user = (User) session.getAttribute("loginUser");


			Article article = new Article();
			article.setTitle(request.getParameter("title"));
			article.setCategory(request.getParameter("category"));
			article.setText(request.getParameter("text"));
			article.setUserId(user.getId());



			new ArticleService().register(article);


			response.sendRedirect("home");
		} else {
			session.setAttribute("errorMessages", articles);
			response.sendRedirect("articleup");

		}
	}

	private boolean isValid(HttpServletRequest request, List<String>
	messages) {

		String text = request.getParameter("text");
		String title = request.getParameter("title");
		String category = request.getParameter("category");


		if (StringUtils.isEmpty(text) == true) {
			messages.add("記事内容を入力してください");
			}
		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを選択してください");
			}
		if (StringUtils.isEmpty(title) == true) {
			messages.add("タイトルを入力してください");
			}
		if (50 < text.length()) {
			messages.add("タイトルは50文字以下で入力してください");
			}

		if (1000 < text.length()) {
			messages.add("記事は1000文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
}

