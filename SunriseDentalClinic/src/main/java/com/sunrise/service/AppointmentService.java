package com.sunrise.service;

import java.sql.Date;
import java.sql.Time;

import com.sunrise.dao.AppointmentDAO;
import com.sunrise.model.Appointment;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
    }


    public String registerAppointment(
            int patientId,
            int dentistId,
            int treatmentId,
            Date appointmentDate,
            Time appointmentTime) {

        // Check whether dentist already has an appointment
        boolean isBooked =
                appointmentDAO.isDentistBooked(
                        dentistId,
                        appointmentDate,
                        appointmentTime
                );

        if (isBooked) {
            return null;
        }


        Appointment appointment =
                new Appointment(
                        null,
                        patientId,
                        dentistId,
                        treatmentId,
                        appointmentDate,
                        appointmentTime,
                        "SCHEDULED"
                );

        return appointmentDAO.addAppointment(appointment);
    }


    public boolean updateAppointmentStatus(
            int appointmentId,
            String status) {

        if (!status.equals("COMPLETED")
                && !status.equals("CANCELLED")) {

            return false;
        }

        return appointmentDAO.updateAppointmentStatus(
                appointmentId,
                status
        );
    }
}