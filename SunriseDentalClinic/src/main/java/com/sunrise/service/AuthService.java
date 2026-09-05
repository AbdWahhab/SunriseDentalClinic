package com.sunrise.service;

import com.sunrise.dao.UserDAO;
import com.sunrise.model.User;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        userDAO = new UserDAO();
    }


    public User login(String username, String password) {

        if (username == null ||
            username.trim().isEmpty() ||
            password == null ||
            password.trim().isEmpty()) {

            return null;
        }

        return userDAO.validateUser(
                username.trim(),
                password
        );
    }
}