package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			session.setAttribute("ip", req.getRemoteAddr());
		}
		else {
			if(session.getAttribute("ip") != req.getRemoteAddr()) {
				session.invalidate();
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
		
		try {
		String username = req.getParameter("username");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String passwordCopy = req.getParameter("passwordCopy");
		
		UserManager.getInstance().register(username, firstName, lastName, password, email, passwordCopy);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			RequestDispatcher rd=req.getRequestDispatcher("/register.jsp");            
			rd.include(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}
	
}
