package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserManager;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(username == null || password == null) {
			throw new IllegalArgumentException("Username/password cannot be empty!");
		}
		boolean hasLogged = UserManager.getInstance().login(username, password);
		
		if(!hasLogged) {
			req.setAttribute("error","Invalid Username or Password");
			RequestDispatcher rd=req.getRequestDispatcher("/login.jsp");            
			rd.include(req, resp);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("geted");
	}
}
