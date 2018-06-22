package controller;

import java.io.IOException;

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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
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

		String id = request.getParameter("id");
		UserDao userDao = new UserDao();
		User userDetail = userDao.findByUserDetail(id);

		request.setAttribute("userDetail", userDetail);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_delete.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String calcel = request.getParameter("cancel");
		String delete = request.getParameter("delete");

		if(delete != null) {
			String id = request.getParameter("id");
			UserDao userDao = new UserDao();
			userDao.userDelete(id);
			response.sendRedirect("UserListServlet");
		} else if(calcel != null){
			response.sendRedirect("UserListServlet");
		}

	}

}
