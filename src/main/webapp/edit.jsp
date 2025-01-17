<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EDIT DETAILS</title>
<style>
*{margin: 0;
	padding:0;
	box-sizing:border-box;
}

body {
    font-family: "Times New Roman", Times, serif;
    background-color: #f4f4f9;
    color: #333;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

div {
    background-color: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
    text-align: center;
    width: 100%;
    max-width: 400px;
}

h2 {
    color: #007bff; 
    margin-bottom: 20px;
    margin-right:55px;
}

input {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}
.submit {
    padding: 10px 20px;
    font-size: 1em;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 5px;
  
    margin-top: 20px;
    width: 100%;
}
form section{
	display:flex;
	flex-direction:row;
	align-items: center;
	 justify-content: space-between;
	 margin-bottom:20px;
}
form>section input[type="radio"]{
			width: 25px;  
            height: 25px;
            
            
}
form>section label{
	font-family: "Times New Roman", Times, serif;
	
	 display: block;
	  margin-top: 4px;
	  text-align: center;
}
a {
    color: #007bff;
    text-decoration: none;
    font-weight: bold;
    
    margin-top: 20px;
}

a:hover {
    text-decoration: underline;
}

</style>
</head>
<body>
<h2>EDIT DETAILS</h2>
<div>
	<form action="editD" method="post">
		 <section>
		<label for="colName">Choose an option:</label><br>
        <input type="radio" id="option1" name="colName" value="name">
        <label for="option1">name</label>

        <input type="radio" id="option2" name="colName" value="email">
        <label for="option2">email</label>

        <input type="radio" id="option3" name="colName" value="password">
        <label for="option3">password</label>
		</section>
		NEW VALUE: <input type="text" name="updtvalue">
		CONFIRM_PASSWORD : <input type="password" name="password">
		
		<input type="submit" class="submit"value="UPDATE">
		<a href="/Registerapp/homepage.jsp">HomePage</a>
	</form>
		
	<%if(session.getAttribute("SuccessMsg") == null){
		out.println("<h2 style='color:red;margin-bottom:10px;></h2>");
	}else if (session.getAttribute("SuccessMsg") != null) {
        String msg = (String) session.getAttribute("SuccessMsg");
        out.println("<h2 style='color:red;margin-bottom:10px;'>" + msg + "</h2>");
       
        session.removeAttribute("SuccessMsg"); 
      }else{
    	  
              String msg = (String) session.getAttribute("ErrorMsg");
              out.println("<h2 style='color:red;margin-bottom:10px;'>" + msg + "</h2>");
             
              session.removeAttribute("ErrorMsg"); 
            
      } %>
	
	</div>
	
	
</body>
</html>
