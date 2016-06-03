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
import bulletin_board.beans.Comment;
import bulletin_board.beans.User;
import bulletin_board.service.CommentService;


@WebServlet(urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
	IOException {

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
	IOException {
		HttpSession session = request.getSession();

		List<String> comments = new ArrayList<String>();

		if (isValid(request, comments ) == true) {

			User user = (User) session.getAttribute("loginUser");
			Article article =  (Article) session.getAttribute("articles");


			Comment comment = new Comment();
			comment.setUserId(user.getId());
			comment.setCommentText(request.getParameter("commentText"));
			int articleId = Integer.parseInt(request.getParameter("article_id"));
			comment.setarticleId(articleId);

			new CommentService().register(comment);


			response.sendRedirect("home");
		} else {
			session.setAttribute("errorMessages", comments);
			response.sendRedirect("home");

		}
	}

	private boolean isValid(HttpServletRequest request, List<String>
	messages) {

		String commentText = request.getParameter("commentText");



		if (StringUtils.isEmpty(commentText) == true) {
			messages.add("コメント内容を入力してください");
			}

		if (500 < commentText.length()) {
			messages.add("コメント内容は500文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
}

