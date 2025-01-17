package com.Prefinal_Register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/prefinal";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("uname");
        String email = req.getParameter("uemail");
        String password = req.getParameter("upwd");
        String phn = req.getParameter("uphone");
         

        req.setCharacterEncoding("UTF-8");

        try {

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Driver not found");
            e.printStackTrace();
            
            return; 
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        	Long phone = Long.parseLong(phn);
            String checkSql = "SELECT phone FROM register_table WHERE phone = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setLong(1, phone);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                    	HttpSession session=req.getSession();
                        session.setAttribute("ErrorMsg", "User Already Exist !");
                        resp.sendRedirect("index.jsp");
                        return;
                    } else {
       
                        String insertSql = "INSERT INTO register_table (name, email, phone, password) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                            insertStatement.setString(1, name);
                            insertStatement.setString(2, email);
                            insertStatement.setLong(3, phone);
                            insertStatement.setString(4, password);
                            insertStatement.executeUpdate();

                            HttpSession session=req.getSession();
                            session.setAttribute("sucessmsg", "Registration success!");
                            resp.sendRedirect("index.jsp");
                        } catch (SQLException e) {
                            System.out.println("SQL error during insertion: " );
                            e.printStackTrace();
                          
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL error during phone check: ");
                e.printStackTrace();
                
            }
        } catch (SQLException  | NumberFormatException e) {
            System.out.println("Database connection error: ");
            System.out.println("servlet number retrieve issue");
            e.printStackTrace();
            
        }
    }
}

    

