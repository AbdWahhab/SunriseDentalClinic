package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.DashboardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedUser") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        DashboardDAO dashboardDAO =
                new DashboardDAO();

        request.setAttribute(
                "totalPatients",
                dashboardDAO.getTotalPatients()
        );

        request.setAttribute(
                "totalDentists",
                dashboardDAO.getTotalDentists()
        );

        request.setAttribute(
                "totalAppointments",
                dashboardDAO.getTotalAppointments()
        );

        request.setAttribute(
                "scheduledAppointments",
                dashboardDAO.getScheduledAppointments()
        );

        request.setAttribute(
                "completedAppointments",
                dashboardDAO.getCompletedAppointments()
        );

        request.setAttribute(
                "cancelledAppointments",
                dashboardDAO.getCancelledAppointments()
        );

        request.setAttribute(
                "totalRevenue",
                dashboardDAO.getTotalRevenue()
        );

        request.getRequestDispatcher("dashboard.jsp")
               .forward(request, response);
    }
}