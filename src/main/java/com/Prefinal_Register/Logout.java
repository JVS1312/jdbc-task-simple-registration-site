package com.Prefinal_Register;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Long phone = (Long) session.getAttribute("phone");
		String password = (String) session.getAttribute("password");
		System.out.println(phone);
		session.invalidate();
		
         resp.sendRedirect("/Registerapp/logout.jsp");
	}
}
