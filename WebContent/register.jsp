<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="register" method="post" required>
	Username <input type="text" name = "username" required><br>
   	Password <input type="password" name = "password" required><br>
   	Password Copy <input type="password" name = "passwordCopy" required><br>
   	First Name <input type="text" name = "firstName" required><br>
   	Last Name <input type="text" name = "lastName" required><br>
   	Email <input type="text" name = "email" required><br>
   	<button type="submit">Register</button>
   	</form>
<%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null) {
out.println("<font color=red size=4px>"+login_msg+"</font>");
} else {
	out.println("Successful registration");
}
%>
</body>
</body>
</html>