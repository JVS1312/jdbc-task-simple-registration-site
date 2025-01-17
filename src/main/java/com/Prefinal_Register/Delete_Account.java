package com.Prefinal_Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/accdelete")
public class Delete_Account extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/prefinal?user=postgres&password=root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("login.html");
            return;
        }

        long phone = (long) session.getAttribute("phone");
        String password = (String) session.getAttribute("password");


        PrintWriter writer = resp.getWriter();

        if (phone == 0 || password == null ) {
            writer.println("<html><body><h2>Invalid input or session. Please try again.</h2></body></html>");
            return;
        }      
           try {
                Class.forName("org.postgresql.Driver");
                System.out.println("PostgreSQL JDBC Driver registered!");

                try (Connection connection = DriverManager.getConnection(DB_URL)) {
                    System.out.println("Database connected");

                    String sql = "DELETE FROM register_table WHERE phone = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setLong(1, phone);

                        int rowsAffected = statement.executeUpdate();
                        if (rowsAffected > 0) {
                            session.invalidate(); 
                            writer.println("<html><body>");
                            writer.println("<h1>Account deleted successfully</h1>");
                            writer.println("<a href=\"/Registerapp/index.jsp\">Register again</a>");
                            writer.println("</body></html>");
                        } else {
                            writer.println("<html><body>");
                            writer.println("<h1>No matching record found for: " + phone + "</h1>");
                            writer.println("<a href=\"/Registerapp/homepage.jsp\">Homepage</a>");
                            writer.println("</body></html>");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("SQL error");
                    e.printStackTrace();
                    writer.println("<html><body><h2>An error occurred during account deletion. Please try again later.</h2></body></html>");
                }
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver error");
                e.printStackTrace();
            }
        } 
    }

