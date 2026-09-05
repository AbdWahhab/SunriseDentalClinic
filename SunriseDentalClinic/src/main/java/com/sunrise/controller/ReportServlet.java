package com.sunrise.controller;

import java.io.IOException;
import java.util.Map;

import com.sunrise.dao.ReportDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/reports")
public class ReportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedUser") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        ReportDAO reportDAO = new ReportDAO();

        Map<String, Integer> appointmentsByDentist =
                reportDAO.getAppointmentsByDentist();

        Map<String, Integer> appointmentsByTreatment =
                reportDAO.getAppointmentsByTreatment();

        int totalBills =
                reportDAO.getTotalBills();

        double totalRevenue =
                reportDAO.getTotalRevenue();

        double averageBillAmount =
                reportDAO.getAverageBillAmount();

        request.setAttribute(
                "appointmentsByDentist",
                appointmentsByDentist
        );

        request.setAttribute(
                "appointmentsByTreatment",
                appointmentsByTreatment
        );

        request.setAttribute(
                "totalBills",
                totalBills
        );

        request.setAttribute(
                "totalRevenue",
                totalRevenue
        );

        request.setAttribute(
                "averageBillAmount",
                averageBillAmount
        );

        request.getRequestDispatcher("reports.jsp")
               .forward(request, response);
    }
}