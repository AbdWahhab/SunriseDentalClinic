package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.DashboardDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/dashboard")
public class DashboardApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        DashboardDAO dashboardDAO =
                new DashboardDAO();

        int totalPatients =
                dashboardDAO.getTotalPatients();

        int totalDentists =
                dashboardDAO.getTotalDentists();

        int totalAppointments =
                dashboardDAO.getTotalAppointments();

        int scheduledAppointments =
                dashboardDAO.getScheduledAppointments();

        int completedAppointments =
                dashboardDAO.getCompletedAppointments();

        int cancelledAppointments =
                dashboardDAO.getCancelledAppointments();

        double totalRevenue =
                dashboardDAO.getTotalRevenue();


        String json =
                "{"
                + "\"totalPatients\":"
                + totalPatients
                + ","

                + "\"totalDentists\":"
                + totalDentists
                + ","

                + "\"totalAppointments\":"
                + totalAppointments
                + ","

                + "\"scheduledAppointments\":"
                + scheduledAppointments
                + ","

                + "\"completedAppointments\":"
                + completedAppointments
                + ","

                + "\"cancelledAppointments\":"
                + cancelledAppointments
                + ","

                + "\"totalRevenue\":"
                + totalRevenue
                + "}";


        response.getWriter().write(json);
    }
}