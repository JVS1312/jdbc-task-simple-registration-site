<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" />
<title>homepage</title>

<link rel="stylesheet" href="./homepage.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
	integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<%
	if (session == null) {
		response.sendRedirect("/Registerapp/logout.jsp");
		return;
	}
	%>
	<div class="leftside">
		<span><i class="fa-solid fa-house"></i> HOMEPAGE</span> <a
			href="/Registerapp/details">DETAILS</a> <a href="edit.jsp">EDIT</a>
	</div>

	<div class="rightside">
		<i class="fa-regular fa-user"> </i> <span
			style="text-align: right; font-size: 35px;">User</span>
		<button id="accd">Delete Account</button>
		<a href="/Registerapp/logout"> logout</a>
	</div>
	<script src="./homepage.js"></script>
</body>
</html>
