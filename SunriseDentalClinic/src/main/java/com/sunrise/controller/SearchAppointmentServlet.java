package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.AppointmentDAO;
import com.sunrise.model.AppointmentDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/searchAppointment")
public class SearchAppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String appointmentNumber =
                request.getParameter("appointmentNumber");

        if (appointmentNumber != null && !appointmentNumber.trim().isEmpty()) {

            AppointmentDAO appointmentDAO = new AppointmentDAO();

            AppointmentDetails details =
                    appointmentDAO.getAppointmentByNumber(appointmentNumber);

            if (details != null) {

                request.setAttribute("appointment", details);

            } else {

                request.setAttribute(
                        "errorMessage",
                        "Appointment not found."
                );
            }
        }

        request.getRequestDispatcher("searchAppointment.jsp")
               .forward(request, response);
    }
}