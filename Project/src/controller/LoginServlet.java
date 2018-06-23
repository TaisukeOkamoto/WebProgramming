package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher Dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_login.jsp");
		Dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes;

		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));

			String encryptedPassword = DatatypeConverter.printHexBinary(bytes);

			UserDao userDao = new UserDao();

			User user = userDao.findByLoginInfo(loginId,encryptedPassword);

			if(user == null) {
				request.setAttribute("errMsg","ログインIDまたはパスワードが異なります");

				RequestDispatcher Dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_login.jsp");
				Dispatcher.forward(request, response);
				return;
			}

			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);
			response.sendRedirect("UserListServlet");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
