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
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("userInfo");

		if(user == null) {
			response.sendRedirect("LoginServlet");
			return;
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_registration.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String loginId = request.getParameter("id");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String name = request.getParameter("name");

        try {
    		String StringDate = request.getParameter("birthDate");
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = sdFormat.parse(StringDate);

    		UserDao userDao = new UserDao();
    		userDao.userRegistration(loginId, password, name, birthDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        response.sendRedirect("UserListServlet");
	}

}
