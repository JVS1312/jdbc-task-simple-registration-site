package com.Prefinal_Register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editD")
public class EditDetails extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/prefinal?user=postgres&password=root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // Check if session 
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        Long phone = (Long) session.getAttribute("phone");
        String password = (String) session.getAttribute("password");
        
        String colName = req.getParameter("colName");
        String newValue = req.getParameter("updtvalue");
        String confirmPwd = req.getParameter("password");

        if (phone != null && password != null && password.equals(confirmPwd)) {
            try {
                Class.forName("org.postgresql.Driver");
                System.out.println("PostgreSQL JDBC Driver registered!");
            } catch (ClassNotFoundException e) {
                System.out.println("PostgreSQL JDBC Driver error");
                e.printStackTrace();
                
                return;
            }

            try (Connection connection = DriverManager.getConnection(DB_URL)) {
                System.out.println("Database connected");
                
                String sql = "UPDATE register_table SET " + colName + " = ? WHERE phone = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, newValue);
                    statement.setLong(2, phone);

                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        session.setAttribute("SuccessMsg", "Details updated successfully");
                        resp.sendRedirect("edit.jsp");
                    } else {
                        session.setAttribute("ErrorMsg", "Update unsuccessful, try again");
                        resp.sendRedirect("edit.jsp");
                    }
                }
            } catch (Exception e) {
                System.out.println("SQL error");
                e.printStackTrace();
                session.setAttribute("ErrorMsg", "Update unsuccessful due to database error");
                resp.sendRedirect("edit.jsp");
            }
        } else {
            session.setAttribute("ErrorMsg", "Incorrect password, please try again");
            resp.sendRedirect("edit.jsp");
        }
    }
}
