package com.sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.sunrise.util.DBConnection;

public class ReportDAO {

    public Map<String, Integer> getAppointmentsByDentist() {

        Map<String, Integer> data =
                new LinkedHashMap<>();

        String sql =
                "SELECT d.dentist_name, COUNT(*) AS total " +
                "FROM appointments a " +
                "JOIN dentists d ON a.dentist_id = d.dentist_id " +
                "GROUP BY d.dentist_id, d.dentist_name " +
                "ORDER BY total DESC";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                data.put(
                        resultSet.getString("dentist_name"),
                        resultSet.getInt("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public Map<String, Integer> getAppointmentsByTreatment() {

        Map<String, Integer> data =
                new LinkedHashMap<>();

        String sql =
                "SELECT t.treatment_name, COUNT(*) AS total " +
                "FROM appointments a " +
                "JOIN treatments t ON a.treatment_id = t.treatment_id " +
                "GROUP BY t.treatment_id, t.treatment_name " +
                "ORDER BY total DESC";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                data.put(
                        resultSet.getString("treatment_name"),
                        resultSet.getInt("total")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    public int getTotalBills() {

        String sql =
                "SELECT COUNT(*) FROM bills";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    public double getTotalRevenue() {

        String sql =
                "SELECT COALESCE(SUM(total_amount), 0) FROM bills";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }


    public double getAverageBillAmount() {

        String sql =
                "SELECT COALESCE(AVG(total_amount), 0) FROM bills";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}