package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");

		if(user == null) {
			response.sendRedirect("loginServlet");
			return;
		}

		String id = request.getParameter("id");

		UserDao userdao = new UserDao();
		User updateUser = userdao.getUpdateUserInfo(id);
		request.setAttribute("updateUser", updateUser);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		String passwordInput = request.getParameter("passwordInput");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String name = request.getParameter("name");
		String birthDateStr = request.getParameter("birthDate");

        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date utilBirthDate;

		try {
				utilBirthDate = sdFormat.parse(birthDateStr);

				String id = request.getParameter("id");
				UserDao userdao = new UserDao();
				User updateUser = userdao.getUpdateUserInfo(id);
				updateUser.setName(name);
				updateUser.setBirth_date(utilBirthDate);
				request.setAttribute("updateUser", updateUser);

				if(!(passwordInput.equals(passwordConfirm))) {
					request.setAttribute("passwordDifferentMessage", "パスワードとパスワード（確認）が異なります。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp");
					dispatcher.forward(request, response);
					return;
				} else if (name.equals("") || birthDateStr.equals("")) {
					request.setAttribute("inputEmptyMassage", "未入力項目があります。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp");
					dispatcher.forward(request, response);
					return;
				} else if(passwordInput.equals("")) {
					userdao.setUpdateUserInfo(loginId, name, utilBirthDate);
					response.sendRedirect("UserListServlet");
					return;
				}
				userdao.setUpdateUserInfo(loginId, passwordInput, name, utilBirthDate);
				response.sendRedirect("UserListServlet");
		} catch (ParseException e) {
			request.setAttribute("dateTypeErrMessage", "日付の形式が異なります。「yyyy/mm/dd」形式で入力してください。");
			String id = request.getParameter("id");
			UserDao userdao = new UserDao();
			User updateUser = userdao.getUpdateUserInfo(id);
			updateUser.setName(name);
			updateUser.setBirth_date(null);
			request.setAttribute("updateUser", updateUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_update.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}

}
