package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.dao.PatientDAO;
import com.sunrise.model.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registerPatient")
public class RegisterPatientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String patientName = request.getParameter("patientName");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");

        Patient patient = new Patient(
                patientName,
                address,
                contactNumber
        );

        PatientDAO patientDAO = new PatientDAO();

        boolean success = patientDAO.addPatient(patient);

        if (success) {

            request.setAttribute(
                    "successMessage",
                    "Patient registered successfully."
            );

        } else {

            request.setAttribute(
                    "errorMessage",
                    "Patient registration failed."
            );
        }

        request.getRequestDispatcher("registerPatient.jsp")
               .forward(request, response);
    }
}