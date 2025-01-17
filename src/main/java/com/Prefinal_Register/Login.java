package com.Prefinal_Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/prefinal?user=postgres&password=root";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String phn = req.getParameter("phone");
        String password = req.getParameter("password");

        System.out.println(" phone: " + phn);
        System.out.println(" password: " + password);

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver registered");
        } catch (ClassNotFoundException e) {
            System.out.println("Error Driver");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            System.out.println("Database connected!");
            Long phone = Long.parseLong(phn);
            String sql = "SELECT phone,password FROM register_table WHERE phone = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, phone);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        System.out.println("Login successful.");

                        HttpSession session = req.getSession();
                        session.setAttribute("phone", phone);
                        session.setAttribute("password", password);
                        
                        RequestDispatcher rDispatcher = req.getRequestDispatcher("homepage.jsp");
                        rDispatcher.forward(req, res);
                    } else {
                    
                        System.out.println("Login failed");
                        HttpSession session=req.getSession();
                        session.setAttribute("ErrorMsg", "Invalid phone or password");
                        RequestDispatcher rDispatcher = req.getRequestDispatcher("login.jsp");
                        rDispatcher.forward(req, res);
                        
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("SQL error");
            e.printStackTrace();
            RequestDispatcher rDispatcher = req.getRequestDispatcher("login.jsp");
            rDispatcher.forward(req, res);
        }
    }
}
