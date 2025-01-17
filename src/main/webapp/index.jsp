<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="./register.css" />
<title>Register Page</title>
</head>
<body>
	<h2>REGISTRATION PAGE</h2>

	<div class="maindiv">

		<form action="register" method="post">
			<span class="label-text">NAME: </span> <input type="text"
				name="uname" /> <span class="errormsg"></span> <span
				class="label-text">EMAIL: </span><input type="text" name="uemail" />
			<span class="errormsg"></span> <span class="label-text">PHONE:
			</span> <input type="number" name="uphone" /> <span class="errormsg"></span>
			<span class="label-text">PASSWORD: </span><input type="password"
				name="upwd" /> <span class="errormsg"></span> <input type="submit"
				class="register" value="register" /><br />
		</form>
		<span class="label-text pp">Already Have a Account?</span><a
			href="/Registerapp/login.jsp">LOGIN</a>
		<div class="msg">
			<%
			if (session != null && session.getAttribute("sucessmsg") != null) {
				String msg = (String) session.getAttribute("sucessmsg");
				out.println("<h2 style='color:red;margin-top:10px;margin-bottom:10px;'>" + msg + "</h2>");

				session.removeAttribute("sucessmsg");
			} else if (session != null && session.getAttribute("ErrorMsg") != null) {
				String msg = (String) session.getAttribute("ErrorMsg");
				out.println("<h2 style='color:red;margin-top:10px;margin-bottom:10px;'>" + msg + "</h2>");

				session.removeAttribute("ErrorMsg");
			}
			%>
		</div>
	</div>
	<script src="./register.js"></script>
</body>
</html>
