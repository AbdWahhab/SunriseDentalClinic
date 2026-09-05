package com.sunrise.controller;

import java.io.IOException;
import java.util.List;

import com.sunrise.dao.DentistDAO;
import com.sunrise.model.Dentist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dentists")
public class DentistListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        DentistDAO dentistDAO = new DentistDAO();

        List<Dentist> dentists =
                dentistDAO.getAllDentists();

        request.setAttribute(
                "dentists",
                dentists
        );

        request.getRequestDispatcher("dentistList.jsp")
               .forward(request, response);
    }
}