package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.AppointmentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/appointmentStatus")
public class AppointmentStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {

            int appointmentId =
                    Integer.parseInt(
                            request.getParameter("appointmentId")
                    );

            String status =
                    request.getParameter("status");

            if (!"COMPLETED".equals(status)
                    && !"CANCELLED".equals(status)) {

                response.sendRedirect("appointments");
                return;
            }

            AppointmentDAO appointmentDAO =
                    new AppointmentDAO();

            appointmentDAO.updateAppointmentStatus(
                    appointmentId,
                    status
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("appointments");
    }
}