package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.model.AppointmentDetails;
import com.sunrise.model.Bill;
import com.sunrise.service.BillingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedUser") == null) {

            response.sendRedirect("login.jsp");
            return;
        }


        String appointmentNumber =
                request.getParameter("appointmentNumber");


        if (appointmentNumber != null &&
                !appointmentNumber.trim().isEmpty()) {

            BillingService billingService =
                    new BillingService();

            AppointmentDetails appointment =
                    billingService.getAppointment(
                            appointmentNumber
                    );


            if (appointment != null) {

                request.setAttribute(
                        "appointment",
                        appointment
                );

            } else {

                request.setAttribute(
                        "errorMessage",
                        "Appointment not found."
                );
            }
        }


        request.getRequestDispatcher("bill.jsp")
               .forward(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("loggedUser") == null) {

            response.sendRedirect("login.jsp");
            return;
        }


        String appointmentNumber =
                request.getParameter("appointmentNumber");


        BillingService billingService =
                new BillingService();


        AppointmentDetails appointment =
                billingService.getAppointment(
                        appointmentNumber
                );


        if (appointment == null) {

            request.setAttribute(
                    "errorMessage",
                    "Appointment not found."
            );

            request.getRequestDispatcher("bill.jsp")
                   .forward(request, response);

            return;
        }


        request.setAttribute(
                "appointment",
                appointment
        );


        String validationMessage =
                billingService.validateBilling(
                        appointment
                );


        if (validationMessage != null) {

            request.setAttribute(
                    "errorMessage",
                    validationMessage
            );

            request.getRequestDispatcher("bill.jsp")
                   .forward(request, response);

            return;
        }


        Bill bill =
                billingService.generateBill(
                        appointment
                );


        if (bill != null) {

            request.setAttribute(
                    "bill",
                    bill
            );

            request.setAttribute(
                    "successMessage",
                    "Bill generated successfully."
            );

        } else {

            request.setAttribute(
                    "errorMessage",
                    "Unable to generate bill."
            );
        }


        request.getRequestDispatcher("bill.jsp")
               .forward(request, response);
    }
}