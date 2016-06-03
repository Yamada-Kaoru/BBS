package bulletin_board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletin_board.beans.User;
import bulletin_board.beans.UserArticle;
import bulletin_board.beans.UserComment;
import bulletin_board.service.ArticleService;
import bulletin_board.service.CommentService;
import bulletin_board.service.SearchArticleService;


@WebServlet(urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
	IOException {

		User user = (User) request.getSession().getAttribute("loginUser");
		String searchCategory = request.getParameter("category");
		String firstday = request.getParameter("firstday");
		String lastday = request.getParameter("lastday");



        List<UserArticle> articles = new ArticleService().getArticle();
        List<UserArticle> searchArticles = new SearchArticleService().getSearchArticle(firstday,lastday,searchCategory);
        List<UserComment> comments = new CommentService().getComment();




        if(searchArticles.isEmpty()){
        	request.setAttribute("articles", articles);
        	request.setAttribute("comments", comments);
        } else {
        		request.setAttribute("articles", searchArticles);
        		request.setAttribute("comments", comments);
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}
