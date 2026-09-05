package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.PatientDAO;
import com.sunrise.model.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerPatient")
public class RegisterPatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String patientName = request.getParameter("patientName");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");

        patientName = patientName == null ? "" : patientName.trim();
        address = address == null ? "" : address.trim();
        contactNumber = contactNumber == null ? "" : contactNumber.trim();

        if (patientName.isEmpty()) {
            request.setAttribute(
                    "errorMessage",
                    "Patient name is required."
            );

            request.getRequestDispatcher("registerPatient.jsp")
                   .forward(request, response);
            return;
        }

        if (patientName.length() < 2 || patientName.length() > 100) {
            request.setAttribute(
                    "errorMessage",
                    "Patient name must contain between 2 and 100 characters."
            );

            request.getRequestDispatcher("registerPatient.jsp")
                   .forward(request, response);
            return;
        }

        if (address.isEmpty()) {
            request.setAttribute(
                    "errorMessage",
                    "Address is required."
            );

            request.getRequestDispatcher("registerPatient.jsp")
                   .forward(request, response);
            return;
        }

        if (address.length() > 255) {
            request.setAttribute(
                    "errorMessage",
                    "Address cannot contain more than 255 characters."
            );

            request.getRequestDispatcher("registerPatient.jsp")
                   .forward(request, response);
            return;
        }

        if (contactNumber.isEmpty()) {
            request.setAttribute(
                    "errorMessage",
                    "Contact number is required."
            );

            request.getRequestDispatcher("registerPatient.jsp")
                   .forward(request, response);
            return;
        }

        if (!contactNumber.matches("^[0-9]{10}$")) {
            request.setAttribute(
                    "errorMessage",
                    "Contact number must contain exactly 10 digits."
            );

            request.getRequestDispatcher("registerPatient.jsp")
                   .forward(request, response);
            return;
        }

        Patient patient = new Patient();

        patient.setPatientName(patientName);
        patient.setAddress(address);
        patient.setContactNumber(contactNumber);

        boolean success = patientDAO.addPatient(patient);

        if (success) {
            request.setAttribute(
                    "successMessage",
                    "Patient registered successfully."
            );
        } else {
            request.setAttribute(
                    "errorMessage",
                    "Patient registration failed. Please try again."
            );
        }

        request.getRequestDispatcher("registerPatient.jsp")
               .forward(request, response);
    }
}