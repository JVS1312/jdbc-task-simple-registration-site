<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>LOGIN PAGE</title>
    <link rel="stylesheet" href="./login.css" />
  </head>
  <body>
    <h1>LOGIN PAGE</h1>
    <div>
      <% 
        if (session != null && session.getAttribute("ErrorMsg") != null) {
          String msg = (String) session.getAttribute("ErrorMsg");
          out.println("<h2 style='color:red;margin-bottom:10px;'>" + msg + "</h2>");
         
          session.removeAttribute("ErrorMsg"); 
        }
      %>

      <form action="login" method="post">
        <label for="phone" class="label-text">PHONE </label>
        <input type="number" id="phone" class="phn" name="phone" required />

        <label for="password" class="label-text">PASSWORD </label>
        <input type="password" id="password" class="phn" name="password" required />

        <input type="submit" class="loginbtn" value="Login" />
      </form>

      <p>
        <span class="label-text">Don't have an account?</span> 
        <a href="/Registerapp/index.jsp">Register</a>
      </p>
    </div>
  </body>
</html>
