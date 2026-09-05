package com.sunrise.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import com.sunrise.service.AppointmentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registerAppointment")
public class RegisterAppointmentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedUser") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        try {

            int patientId =
                    Integer.parseInt(
                            request.getParameter("patientId")
                    );

            int dentistId =
                    Integer.parseInt(
                            request.getParameter("dentistId")
                    );

            int treatmentId =
                    Integer.parseInt(
                            request.getParameter("treatmentId")
                    );

            Date appointmentDate =
                    Date.valueOf(
                            request.getParameter("appointmentDate")
                    );

            Time appointmentTime =
                    Time.valueOf(
                            request.getParameter("appointmentTime")
                            + ":00"
                    );


            AppointmentService appointmentService =
                    new AppointmentService();


            String appointmentNumber =
                    appointmentService.registerAppointment(
                            patientId,
                            dentistId,
                            treatmentId,
                            appointmentDate,
                            appointmentTime
                    );


            if (appointmentNumber != null) {

                request.setAttribute(
                        "successMessage",
                        "Appointment registered successfully. "
                        + "Appointment Number: "
                        + appointmentNumber
                );

            } else {

                request.setAttribute(
                        "errorMessage",
                        "The selected dentist is already booked "
                        + "for this date and time."
                );
            }


        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMessage",
                    "Unable to register appointment. "
                    + "Please check the entered details."
            );
        }


        request.getRequestDispatcher(
                "registerAppointment.jsp"
        ).forward(request, response);
    }
}