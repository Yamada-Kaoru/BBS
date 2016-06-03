package bulletin_board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletin_board.beans.DisplayUser;
import bulletin_board.beans.User;
import bulletin_board.service.DisplayUserService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/revival"})
public class RevivalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected  void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
	IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		User editUsers = UserService.getUser(id);
		new UserService().rivival(editUsers);
		List<DisplayUser> displayUsers = new DisplayUserService().getDisplayUser();
		session.setAttribute("displayUsers", editUsers);
		request.getRequestDispatcher("manage.jsp").forward(request, response);

	}

	}

