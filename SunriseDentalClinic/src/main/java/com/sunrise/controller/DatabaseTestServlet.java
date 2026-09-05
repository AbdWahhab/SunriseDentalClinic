package com.sunrise.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.sunrise.util.DBConnection;

/**
 * Servlet implementation class DatabaseTestServlet
 */
@WebServlet("/dbtest")
public class DatabaseTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            Connection connection = DBConnection.getConnection();

            response.getWriter().println("<h1>Database Connected Successfully!</h1>");

            connection.close();

        } catch (SQLException e) {
            response.getWriter().println("<h1>Database Connection Failed</h1>");
            response.getWriter().println("<p>" + e.getMessage() + "</p>");
        }
    }

}
