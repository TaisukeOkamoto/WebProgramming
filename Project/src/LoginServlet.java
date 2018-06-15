

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();


		for(User u: userList) {
			if(id.equals(u.getLogin_id()) && password.equals(u.getPassword())) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_list.jsp");
				dispatcher.forward(request, response);
			}
		}
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
//		dispatcher.forward(request, response);
	}

}
