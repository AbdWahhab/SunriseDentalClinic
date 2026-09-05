package com.sunrise.controller;

import java.io.IOException;
import java.util.List;

import com.sunrise.dao.PatientDAO;
import com.sunrise.model.Patient;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/patients")
public class PatientListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        PatientDAO patientDAO = new PatientDAO();

        List<Patient> patients =
                patientDAO.getAllPatients();

        request.setAttribute(
                "patients",
                patients
        );

        request.getRequestDispatcher("patientList.jsp")
               .forward(request, response);
    }
}