package com.sunrise.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

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

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedUser") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        String patientIdText =
                request.getParameter("patientId");

        String dentistIdText =
                request.getParameter("dentistId");

        String treatmentIdText =
                request.getParameter("treatmentId");

        String appointmentDateText =
                request.getParameter("appointmentDate");

        String appointmentTimeText =
                request.getParameter("appointmentTime");


        if (patientIdText == null ||
                patientIdText.isBlank()) {

            request.setAttribute(
                    "errorMessage",
                    "Please select a valid patient."
            );

            forwardToForm(request, response);
            return;
        }


        if (dentistIdText == null ||
                dentistIdText.isBlank()) {

            request.setAttribute(
                    "errorMessage",
                    "Please select a dentist."
            );

            forwardToForm(request, response);
            return;
        }


        if (treatmentIdText == null ||
                treatmentIdText.isBlank()) {

            request.setAttribute(
                    "errorMessage",
                    "Please select a treatment."
            );

            forwardToForm(request, response);
            return;
        }


        if (appointmentDateText == null ||
                appointmentDateText.isBlank()) {

            request.setAttribute(
                    "errorMessage",
                    "Appointment date is required."
            );

            forwardToForm(request, response);
            return;
        }


        if (appointmentTimeText == null ||
                appointmentTimeText.isBlank()) {

            request.setAttribute(
                    "errorMessage",
                    "Appointment time is required."
            );

            forwardToForm(request, response);
            return;
        }


        try {

            int patientId =
                    Integer.parseInt(patientIdText);

            int dentistId =
                    Integer.parseInt(dentistIdText);

            int treatmentId =
                    Integer.parseInt(treatmentIdText);


            if (patientId <= 0) {

                request.setAttribute(
                        "errorMessage",
                        "Please select a valid patient."
                );

                forwardToForm(request, response);
                return;
            }


            if (dentistId <= 0) {

                request.setAttribute(
                        "errorMessage",
                        "Please select a valid dentist."
                );

                forwardToForm(request, response);
                return;
            }


            if (treatmentId <= 0) {

                request.setAttribute(
                        "errorMessage",
                        "Please select a valid treatment."
                );

                forwardToForm(request, response);
                return;
            }


            LocalDate selectedDate =
                    LocalDate.parse(
                            appointmentDateText
                    );

            LocalDate today =
                    LocalDate.now();


            if (selectedDate.isBefore(today)) {

                request.setAttribute(
                        "errorMessage",
                        "Appointment date cannot be in the past."
                );

                forwardToForm(request, response);
                return;
            }


            Date appointmentDate =
                    Date.valueOf(selectedDate);


            Time appointmentTime =
                    Time.valueOf(
                            appointmentTimeText + ":00"
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


        } catch (NumberFormatException e) {

            request.setAttribute(
                    "errorMessage",
                    "Invalid patient, dentist, or treatment selection."
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "errorMessage",
                    "Invalid appointment date or time."
            );

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "errorMessage",
                    "Unable to register appointment. "
                    + "Please try again."
            );
        }


        forwardToForm(request, response);
    }


    private void forwardToForm(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "registerAppointment.jsp"
        ).forward(request, response);
    }
}