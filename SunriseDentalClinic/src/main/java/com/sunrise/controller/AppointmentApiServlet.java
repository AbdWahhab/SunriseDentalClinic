package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.AppointmentDAO;
import com.sunrise.model.AppointmentDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/appointment")
public class AppointmentApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String appointmentNumber =
                request.getParameter("number");

        if (appointmentNumber == null ||
                appointmentNumber.trim().isEmpty()) {

            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().write(
                    "{\"error\":\"Appointment number is required\"}"
            );

            return;
        }


        AppointmentDAO appointmentDAO =
                new AppointmentDAO();

        AppointmentDetails appointment =
                appointmentDAO.getAppointmentByNumber(
                        appointmentNumber.trim()
                );


        if (appointment == null) {

            response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
            );

            response.getWriter().write(
                    "{\"error\":\"Appointment not found\"}"
            );

            return;
        }


        String json =
                "{"
                + "\"appointmentNumber\":\""
                + escapeJson(
                        appointment.getAppointmentNumber()
                )
                + "\","

                + "\"patientName\":\""
                + escapeJson(
                        appointment.getPatientName()
                )
                + "\","

                + "\"contactNumber\":\""
                + escapeJson(
                        appointment.getContactNumber()
                )
                + "\","

                + "\"dentistName\":\""
                + escapeJson(
                        appointment.getDentistName()
                )
                + "\","

                + "\"treatmentName\":\""
                + escapeJson(
                        appointment.getTreatmentName()
                )
                + "\","

                + "\"appointmentDate\":\""
                + appointment.getAppointmentDate()
                + "\","

                + "\"appointmentTime\":\""
                + appointment.getAppointmentTime()
                + "\","

                + "\"status\":\""
                + escapeJson(
                        appointment.getStatus()
                )
                + "\","

                + "\"treatmentCost\":"
                + appointment.getTreatmentCost()
                + ","

                + "\"consultationFee\":"
                + appointment.getConsultationFee()
                + ","

                + "\"totalCost\":"
                + appointment.getTotalCost()

                + "}";


        response.getWriter().write(json);
    }


    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}