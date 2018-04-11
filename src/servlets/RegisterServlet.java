package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserManager;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String passwordCopy = req.getParameter("passwordCopy");
		UserManager.getInstance().register(username, firstName, lastName, password, email, passwordCopy);
	}
}
