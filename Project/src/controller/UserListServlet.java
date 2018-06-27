package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
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
			response.sendRedirect("LoginServlet");
			return;
		}

		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();

		request.setAttribute("userList", userList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String inputId = request.getParameter("inputId");
		String inputName = request.getParameter("inputName");

		String birthDateFrom = request.getParameter("birthDateFrom");
		String birthDateTo = request.getParameter("birthDateTo");

		Date birthDateFromD = null;
		Date birthDateToD = null;

		try {
			if(!(birthDateFrom.equals("") && birthDateTo.equals(""))) {
		        SimpleDateFormat sdFormatFrom = new SimpleDateFormat("yyyy/MM/dd");
		        birthDateFromD = sdFormatFrom.parse(birthDateFrom);

		        SimpleDateFormat sdFormatTo = new SimpleDateFormat("yyyy/MM/dd");
				birthDateToD = sdFormatTo.parse(birthDateTo);
			}
			UserDao userDao = new UserDao();

			//ユーザー検索
			List<User> userList = userDao.searchUser(inputId,inputName,birthDateFromD,birthDateToD);
			request.setAttribute("userList", userList);
			request.setAttribute("inputId", inputId);
			request.setAttribute("inputName", inputName);
			request.setAttribute("birthDateFrom", birthDateFrom);
			request.setAttribute("birthDateTo", birthDateTo);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp");
			dispatcher.forward(request, response);
		} catch (ParseException e) {
			request.setAttribute("inputDateErrMessage", "日付の形式が異なります");;
			request.setAttribute("inputId", inputId);
			request.setAttribute("inputName", inputName);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp");
			dispatcher.forward(request, response);
		}


	}

}
