

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Human;

/**
 * Servlet implementation class SessionElServlet
 */
@WebServlet("/SessionElServlet")
public class SessionElServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Human human = new Human("山田太郎",20);
		HttpSession session = request.getSession();

		session.setAttribute("human", human);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/SessionEl.jsp");
		dispatcher.forward(request, response);

		session.removeAttribute("human");
	}

}
