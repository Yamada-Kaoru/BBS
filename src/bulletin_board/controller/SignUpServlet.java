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

import bulletin_board.beans.Branch;
import bulletin_board.beans.Possition;
import bulletin_board.beans.User;
import bulletin_board.service.BranchService;
import bulletin_board.service.PossitionService;
import bulletin_board.service.UserService;

@WebServlet(urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		List<Branch> branches = new BranchService().getBranch();
		session.setAttribute("branches", branches);
		List<Possition> possitions = new PossitionService().getPossition();
		session.setAttribute("possitions", possitions);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();


		User user = new User();
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		user.setBranchId(request.getParameter("branch_id"));
		user.setPossitionId(request.getParameter("possition_id"));
		user.setStatus(request.getParameter("status"));


		if (isValid(request, messages) == true) {
			new UserService().register(user);
			response.sendRedirect("manage");
		} else {
			session.setAttribute("temporalUser", user);

			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");



		if (name.length() > 10) {
			messages.add("名前が10文字以上です");
		}
		if (StringUtils.isEmpty(account)) {
			messages.add("ログインIDを入力してください");
		}
		if (!account.matches("[0-9a-zA-Z_]{6,20}$")) {
			messages.add("無効なログインIDです");
		}
		if (StringUtils.isEmpty(password)) {
			messages.add("パスワードを入力してください");
		}
		if (password.length() < 6) {
			messages.add("パスワードが6文字以下です");
		}
		if (!password.equals(checkPassword)) {
			messages.add("確認パスワードが一致しません");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
