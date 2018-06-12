

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Human;

/**
 * Servlet implementation class SessionScopeServlet
 */
@WebServlet("/SessionScopeServlet")
public class SessionScopeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionScopeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Human human = new Human("山田太郎",20);

		HttpSession session = request.getSession();

		session.setAttribute("human",human);

		Human h = (Human)session.getAttribute("human");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/RequestSessionScope.jsp");
		dispatcher.forward(request,response);

		session.removeAttribute("human");

	}

}
