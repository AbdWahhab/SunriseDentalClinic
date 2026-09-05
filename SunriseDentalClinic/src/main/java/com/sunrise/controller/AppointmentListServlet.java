package com.sunrise.controller;

import java.io.IOException;
import java.util.List;

import com.sunrise.dao.AppointmentDAO;
import com.sunrise.model.AppointmentDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/appointments")
public class AppointmentListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AppointmentDAO appointmentDAO =
                new AppointmentDAO();

        List<AppointmentDetails> appointments =
                appointmentDAO.getAllAppointments();

        request.setAttribute(
                "appointments",
                appointments
        );

        request.getRequestDispatcher("appointmentList.jsp")
               .forward(request, response);
    }
}