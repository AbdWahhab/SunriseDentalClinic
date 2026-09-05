package com.sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.model.Treatment;
import com.sunrise.util.DBConnection;

public class TreatmentDAO {

    public List<Treatment> getAllTreatments() {

        List<Treatment> treatments = new ArrayList<>();

        String sql = "SELECT treatment_id, treatment_name, treatment_cost, consultation_fee FROM treatments";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Treatment treatment = new Treatment(
                        resultSet.getInt("treatment_id"),
                        resultSet.getString("treatment_name"),
                        resultSet.getDouble("treatment_cost"),
                        resultSet.getDouble("consultation_fee")
                );

                treatments.add(treatment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return treatments;
    }
}