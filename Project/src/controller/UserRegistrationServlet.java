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

		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("id");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String name = request.getParameter("name");

        try {
    		String StringDate = request.getParameter("birthDate");

            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date birthDate = sdFormat.parse(StringDate);

    		UserDao userDao = new UserDao();

    		request.setAttribute("loginId", loginId);
    		request.setAttribute("name", name);
    		request.setAttribute("birthDate", birthDate);

    		if (loginId.equals("") || password.equals("") || passwordConfirm.equals("")) {
	    		request.setAttribute("inputEmptyMassage","未入力項目があります。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_registration.jsp");
				dispatcher.forward(request, response);
				return;
    		} else if(!(password.equals(passwordConfirm))) {
    			request.setAttribute("passwordOccupiedMassage","パスワードとパスワード（確認）が異なります。");
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_registration.jsp");
    			dispatcher.forward(request, response);
    			return;
    		} else if (userDao.userCheck(loginId) == true){
    			request.setAttribute("duplicatedLoginIdMessage","このユーザIDはすでに使用されています。");
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_registration.jsp");
    			dispatcher.forward(request, response);
    			return;
    		} else {
    			userDao.userRegistration(loginId, password, name, birthDate);
    	        response.sendRedirect("UserListServlet");
    		}

        } catch (ParseException e) {
			request.setAttribute("dateTypeErrMessage","日付の形式が異なります。「yyyy/mm/dd」形式で入力してください");
    		request.setAttribute("loginId", loginId);
    		request.setAttribute("name", name);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_registration.jsp");
			dispatcher.forward(request, response);
			return;
        }
	}

}
