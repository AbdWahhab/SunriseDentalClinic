package com.sunrise.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sunrise_dental_db";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            System.getenv("SUNRISE_DB_PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {

        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException(
                    "Environment variable SUNRISE_DB_PASSWORD is not configured."
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}