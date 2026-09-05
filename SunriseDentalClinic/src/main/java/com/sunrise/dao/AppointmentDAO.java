package com.sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.sunrise.model.AppointmentDetails;
import java.sql.Statement;
import com.sunrise.model.Appointment;
import com.sunrise.util.DBConnection;

public class AppointmentDAO {

    public String addAppointment(Appointment appointment) {

    String insertSql =
            "INSERT INTO appointments " +
            "(patient_id, dentist_id, treatment_id, " +
            "appointment_date, appointment_time, status) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    String updateSql =
            "UPDATE appointments " +
            "SET appointment_number = ? " +
            "WHERE appointment_id = ?";

    Connection connection = null;

    try {

        connection = DBConnection.getConnection();

        connection.setAutoCommit(false);

        PreparedStatement insertStatement =
                connection.prepareStatement(
                        insertSql,
                        Statement.RETURN_GENERATED_KEYS
                );

        insertStatement.setInt(
                1,
                appointment.getPatientId()
        );

        insertStatement.setInt(
                2,
                appointment.getDentistId()
        );

        insertStatement.setInt(
                3,
                appointment.getTreatmentId()
        );

        insertStatement.setDate(
                4,
                appointment.getAppointmentDate()
        );

        insertStatement.setTime(
                5,
                appointment.getAppointmentTime()
        );

        insertStatement.setString(
                6,
                appointment.getStatus()
        );

        int rows = insertStatement.executeUpdate();

        if (rows > 0) {

            ResultSet generatedKeys =
                    insertStatement.getGeneratedKeys();

            if (generatedKeys.next()) {

                int appointmentId =
                        generatedKeys.getInt(1);

                String appointmentNumber =
                        String.format(
                                "%04d",
                                appointmentId
                        );

                PreparedStatement updateStatement =
                        connection.prepareStatement(
                                updateSql
                        );

                updateStatement.setString(
                        1,
                        appointmentNumber
                );

                updateStatement.setInt(
                        2,
                        appointmentId
                );

                updateStatement.executeUpdate();

                connection.commit();

                return appointmentNumber;
            }
        }

        connection.rollback();

    } catch (Exception e) {

        e.printStackTrace();

        if (connection != null) {

            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    } finally {

        if (connection != null) {

            try {

                connection.setAutoCommit(true);
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return null;
}
    public AppointmentDetails getAppointmentByNumber(String appointmentNumber) {

        String sql =
        		"SELECT a.appointment_id, a.appointment_number, " +
                "p.patient_name, p.address, p.contact_number, " +
                "d.dentist_name, d.specialization, " +
                "t.treatment_name, t.treatment_cost, t.consultation_fee, " +
                "a.appointment_date, a.appointment_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN dentists d ON a.dentist_id = d.dentist_id " +
                "JOIN treatments t ON a.treatment_id = t.treatment_id " +
                "WHERE a.appointment_number = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, appointmentNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AppointmentDetails details = new AppointmentDetails();
                
                details.setAppointmentId(
                        resultSet.getInt("appointment_id"));

                details.setAppointmentNumber(
                        resultSet.getString("appointment_number"));

                details.setPatientName(
                        resultSet.getString("patient_name"));

                details.setAddress(
                        resultSet.getString("address"));

                details.setContactNumber(
                        resultSet.getString("contact_number"));

                details.setDentistName(
                        resultSet.getString("dentist_name"));

                details.setSpecialization(
                        resultSet.getString("specialization"));

                details.setTreatmentName(
                        resultSet.getString("treatment_name"));

                details.setTreatmentCost(
                        resultSet.getDouble("treatment_cost"));

                details.setConsultationFee(
                        resultSet.getDouble("consultation_fee"));

                details.setAppointmentDate(
                        resultSet.getDate("appointment_date"));

                details.setAppointmentTime(
                        resultSet.getTime("appointment_time"));

                details.setStatus(
                        resultSet.getString("status"));

                return details;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean isDentistBooked(
            int dentistId,
            java.sql.Date appointmentDate,
            java.sql.Time appointmentTime) {

        String sql =
                "SELECT COUNT(*) FROM appointments " +
                "WHERE dentist_id = ? " +
                "AND appointment_date = ? " +
                "AND appointment_time = ? " +
                "AND status = 'SCHEDULED'";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, dentistId);
            statement.setDate(2, appointmentDate);
            statement.setTime(3, appointmentTime);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public java.util.List<AppointmentDetails> getAllAppointments() {

        java.util.List<AppointmentDetails> appointments =
                new java.util.ArrayList<>();

        String sql =
                "SELECT a.appointment_id, a.appointment_number, " +
                "p.patient_name, p.address, p.contact_number, " +
                "d.dentist_name, d.specialization, " +
                "t.treatment_name, t.treatment_cost, t.consultation_fee, " +
                "a.appointment_date, a.appointment_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN dentists d ON a.dentist_id = d.dentist_id " +
                "JOIN treatments t ON a.treatment_id = t.treatment_id " +
                "ORDER BY a.appointment_date DESC, a.appointment_time DESC";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                AppointmentDetails details =
                        new AppointmentDetails();

                details.setAppointmentId(
                        resultSet.getInt("appointment_id"));

                details.setAppointmentNumber(
                        resultSet.getString("appointment_number"));

                details.setPatientName(
                        resultSet.getString("patient_name"));

                details.setAddress(
                        resultSet.getString("address"));

                details.setContactNumber(
                        resultSet.getString("contact_number"));

                details.setDentistName(
                        resultSet.getString("dentist_name"));

                details.setSpecialization(
                        resultSet.getString("specialization"));

                details.setTreatmentName(
                        resultSet.getString("treatment_name"));

                details.setTreatmentCost(
                        resultSet.getDouble("treatment_cost"));

                details.setConsultationFee(
                        resultSet.getDouble("consultation_fee"));

                details.setAppointmentDate(
                        resultSet.getDate("appointment_date"));

                details.setAppointmentTime(
                        resultSet.getTime("appointment_time"));

                details.setStatus(
                        resultSet.getString("status"));

                appointments.add(details);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
    
    public boolean updateAppointmentStatus(
            int appointmentId,
            String status) {

        String sql =
                "UPDATE appointments " +
                "SET status = ? " +
                "WHERE appointment_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, status);
            statement.setInt(2, appointmentId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public AppointmentDetails getAppointmentById(int appointmentId) {

        String sql =
                "SELECT a.appointment_id, a.appointment_number, " +
                "p.patient_name, p.address, p.contact_number, " +
                "d.dentist_name, d.specialization, " +
                "t.treatment_name, t.treatment_cost, t.consultation_fee, " +
                "a.appointment_date, a.appointment_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN dentists d ON a.dentist_id = d.dentist_id " +
                "JOIN treatments t ON a.treatment_id = t.treatment_id " +
                "WHERE a.appointment_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, appointmentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                AppointmentDetails details =
                        new AppointmentDetails();

                details.setAppointmentId(
                        resultSet.getInt("appointment_id"));

                details.setAppointmentNumber(
                        resultSet.getString("appointment_number"));

                details.setPatientName(
                        resultSet.getString("patient_name"));

                details.setAddress(
                        resultSet.getString("address"));

                details.setContactNumber(
                        resultSet.getString("contact_number"));

                details.setDentistName(
                        resultSet.getString("dentist_name"));

                details.setSpecialization(
                        resultSet.getString("specialization"));

                details.setTreatmentName(
                        resultSet.getString("treatment_name"));

                details.setTreatmentCost(
                        resultSet.getDouble("treatment_cost"));

                details.setConsultationFee(
                        resultSet.getDouble("consultation_fee"));

                details.setAppointmentDate(
                        resultSet.getDate("appointment_date"));

                details.setAppointmentTime(
                        resultSet.getTime("appointment_time"));

                details.setStatus(
                        resultSet.getString("status"));

                return details;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}