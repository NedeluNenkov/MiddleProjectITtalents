package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Session.validateRequestIp(req, resp);
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			if (username == null || password == null) {
				throw new IllegalArgumentException("Username/password cannot be empty!");
			}
			UserManager.getInstance().login(username, password);
			req.getSession().setAttribute("USER", username);

			req.getRequestDispatcher("main.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
}
