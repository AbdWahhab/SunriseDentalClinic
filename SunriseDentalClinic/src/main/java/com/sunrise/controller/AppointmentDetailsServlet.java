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

@WebServlet("/appointmentDetails")
public class AppointmentDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {

            int appointmentId =
                    Integer.parseInt(
                            request.getParameter("id")
                    );

            AppointmentDAO appointmentDAO =
                    new AppointmentDAO();

            AppointmentDetails appointment =
                    appointmentDAO.getAppointmentById(
                            appointmentId
                    );

            if (appointment == null) {

                response.sendRedirect("appointments");
                return;
            }

            request.setAttribute(
                    "appointment",
                    appointment
            );

            request.getRequestDispatcher(
                    "appointmentDetails.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect("appointments");
        }
    }
}