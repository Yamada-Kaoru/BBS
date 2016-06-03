package bulletin_board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletin_board.beans.Branch;
import bulletin_board.beans.Possition;
import bulletin_board.beans.User;
import bulletin_board.exception.NoRowsUpdateRuntimeException;
import bulletin_board.service.BranchService;
import bulletin_board.service.PossitionService;
import bulletin_board.service.UserService;


@WebServlet(urlPatterns = { "/setting" })
@MultipartConfig(maxFileSize = 100000)
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		User targetUser = UserService.getUser(id);
		session.setAttribute("targetUser", targetUser);

		List<Branch> branches = new BranchService().getBranch();
		session.setAttribute("branches", branches);

		List<Possition> possitions = new PossitionService().getPossition();
		session.setAttribute("possitions", possitions);

		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		String id = request.getParameter("id");

		User editUser = getEditUser(request, id);

		if (isValid(request, messages) == true) {

			try {
				new UserService().update(editUser);

			} catch (NoRowsUpdateRuntimeException e) {
				session.removeAttribute("editUser");
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("setting");
			}



			response.sendRedirect("manage");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("setting");
		}
	}

	private User getEditUser(HttpServletRequest request, String id)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User editUser = new User();

		editUser.setId(Integer.parseInt(id));
		editUser.setName(request.getParameter("name"));
		editUser.setAccount(request.getParameter("account"));
		editUser.setPassword(request.getParameter("password"));
		editUser.setBranchId(request.getParameter("branch_Id"));
		editUser.setPossitionId(request.getParameter("possition_Id"));
		System.out.println(editUser.getBranchId());
		System.out.println(editUser.getPossitionId());

		return editUser;
	}


	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (!password.equals(checkPassword)) {
			messages.add("確認用パスワードが違います");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
