package com.sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunrise.model.Dentist;
import com.sunrise.util.DBConnection;

public class DentistDAO {

    public List<Dentist> getAllDentists() {

        List<Dentist> dentists = new ArrayList<>();

        String sql = "SELECT dentist_id, dentist_name, specialization FROM dentists";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Dentist dentist = new Dentist(
                        resultSet.getInt("dentist_id"),
                        resultSet.getString("dentist_name"),
                        resultSet.getString("specialization")
                );

                dentists.add(dentist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dentists;
    }
}