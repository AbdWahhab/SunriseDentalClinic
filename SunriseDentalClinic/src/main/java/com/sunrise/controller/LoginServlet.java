package com.sunrise.controller;

import java.io.IOException;

import com.sunrise.model.User;
import com.sunrise.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");


        AuthService authService =
                new AuthService();


        User user =
                authService.login(
                        username,
                        password
                );


        if (user != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "loggedUser",
                    user
            );

            response.sendRedirect("dashboard");

        } else {

            request.setAttribute(
                    "errorMessage",
                    "Invalid username or password."
            );

            request.getRequestDispatcher("login.jsp")
                   .forward(request, response);
        }
    }
}