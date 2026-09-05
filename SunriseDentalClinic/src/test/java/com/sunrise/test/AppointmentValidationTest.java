package com.sunrise.test;

import java.sql.Date;
import java.sql.Time;

import com.sunrise.model.Appointment;

public class AppointmentValidationTest {

    public static void main(String[] args) {

        Appointment appointment = new Appointment();

        appointment.setPatientId(1);
        appointment.setDentistId(2);
        appointment.setTreatmentId(3);
        appointment.setAppointmentDate(Date.valueOf("2026-09-10"));
        appointment.setAppointmentTime(Time.valueOf("10:30:00"));
        appointment.setStatus("SCHEDULED");

        boolean passed = true;

        if (appointment.getPatientId() <= 0) {
            passed = false;
        }

        if (appointment.getDentistId() <= 0) {
            passed = false;
        }

        if (appointment.getTreatmentId() <= 0) {
            passed = false;
        }

        if (appointment.getAppointmentDate() == null) {
            passed = false;
        }

        if (appointment.getAppointmentTime() == null) {
            passed = false;
        }

        if (!"SCHEDULED".equals(appointment.getStatus())) {
            passed = false;
        }

        if (passed) {
            System.out.println("TEST PASSED: Appointment data validation is correct.");
        } else {
            System.out.println("TEST FAILED: Appointment data validation failed.");
        }
    }
}