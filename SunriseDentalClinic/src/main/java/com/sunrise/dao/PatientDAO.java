package com.sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.sunrise.model.Patient;
import com.sunrise.util.DBConnection;

public class PatientDAO {

    public boolean addPatient(Patient patient) {

        String sql = "INSERT INTO patients "
                   + "(patient_name, address, contact_number) "
                   + "VALUES (?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, patient.getPatientName());
            statement.setString(2, patient.getAddress());
            statement.setString(3, patient.getContactNumber());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<>();

        String sql =
                "SELECT patient_id, patient_name, address, contact_number " +
                "FROM patients " +
                "ORDER BY patient_name ASC";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Patient patient = new Patient(
                        resultSet.getInt("patient_id"),
                        resultSet.getString("patient_name"),
                        resultSet.getString("address"),
                        resultSet.getString("contact_number")
                );

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}