package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
		Session.validateRequestIp(req, resp);
		
		HttpSession session = req.getSession();
		session.invalidate();
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		} catch (Exception e) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
