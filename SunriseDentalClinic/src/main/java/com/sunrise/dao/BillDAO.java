package com.sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sunrise.model.Bill;
import com.sunrise.util.DBConnection;

public class BillDAO {

    public int createBill(Bill bill) {

        String sql =
                "INSERT INTO bills " +
                "(appointment_id, treatment_cost, consultation_fee, total_amount) " +
                "VALUES (?, ?, ?, ?)";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    )
        ) {

            statement.setInt(1, bill.getAppointmentId());
            statement.setDouble(2, bill.getTreatmentCost());
            statement.setDouble(3, bill.getConsultationFee());
            statement.setDouble(4, bill.getTotalAmount());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                ResultSet generatedKeys =
                        statement.getGeneratedKeys();

                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    
    public boolean billExists(int appointmentId) {

        String sql =
                "SELECT COUNT(*) FROM bills " +
                "WHERE appointment_id = ?";

        try (
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, appointmentId);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}