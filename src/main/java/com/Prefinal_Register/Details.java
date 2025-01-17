package com.Prefinal_Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/details")
public class Details extends HttpServlet {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/prefinal?user=postgres&password=root";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Long phone = (Long) session.getAttribute("phone");
		String password = (String) session.getAttribute("password");
		PrintWriter writer = resp.getWriter();
		System.out.println(" phone: " + phone);
		System.out.println(" password: " + password);

		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("JDBC registered!");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not REGISTERED");
			e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection(DB_URL)) {
			System.out.println("Database connected");
			String sql = "SELECT * FROM register_table WHERE phone = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setLong(1, phone);

				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						writer.print("<html><style> ");
						writer.print(
								"body { font-family: 'Times New Roman', Times, serif; background-color: #f4f4f9; color: #333; padding: 20px; text-align: center; }");
						writer.print("h1 { color: #007bff; margin-bottom: 30px; }");
						writer.print("h3 { margin: 15px 0; text-align: center; }");
						writer.print("form { display: inline-block; margin-top: 20px; }");
						writer.print(
								"input[type='submit'] { padding: 10px 20px; font-size: 1em; color: #fff; background-color: #007bff; border: none; border-radius: 5px; cursor: pointer; margin-top: 20px; display: block; width: 100%; max-width: 200px; }");
						writer.print("input[type='submit']:hover { background-color: #0056b3; }");
						writer.print("</style>");
						writer.print("<body>");
						writer.print("<h1>Details - User ID: " + phone + "</h1>");
						writer.print("<form action='homepage.jsp'>");
						writer.print("<h3>Name: " + resultSet.getString("name") + "</h3>");
						writer.print("<h3>Email: " + resultSet.getString("email") + "</h3>");
						writer.print("<h3>Phone: " + resultSet.getLong("phone") + "</h3>");
						writer.print("<h3>Password: " + resultSet.getString("password") + "</h3>");
						writer.print("<input type='submit' value='Back to Home'>");
						writer.print("</form>");
						writer.print("</body></html>");

						System.out.println(resultSet.getString("name"));
						System.out.println(resultSet.getString("email"));
						System.out.println(resultSet.getLong("phone"));
						System.out.println(resultSet.getString("password"));

					} else {

						resp.sendRedirect("login.jsp");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("SQL error");
			e.printStackTrace();
			resp.sendRedirect("login.jsp");
		}

	}
}
