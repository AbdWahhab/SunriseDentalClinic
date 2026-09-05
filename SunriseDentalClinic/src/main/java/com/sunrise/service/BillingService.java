package com.sunrise.service;

import com.sunrise.dao.AppointmentDAO;
import com.sunrise.dao.BillDAO;
import com.sunrise.model.AppointmentDetails;
import com.sunrise.model.Bill;

public class BillingService {

    private AppointmentDAO appointmentDAO;
    private BillDAO billDAO;

    public BillingService() {
        appointmentDAO = new AppointmentDAO();
        billDAO = new BillDAO();
    }


    public AppointmentDetails getAppointment(String appointmentNumber) {

        if (appointmentNumber == null ||
                appointmentNumber.trim().isEmpty()) {

            return null;
        }

        return appointmentDAO.getAppointmentByNumber(
                appointmentNumber.trim()
        );
    }


    public String validateBilling(
            AppointmentDetails appointment) {

        if (appointment == null) {
            return "Appointment not found.";
        }

        if ("CANCELLED".equalsIgnoreCase(
                appointment.getStatus())) {

            return "A cancelled appointment cannot be billed.";
        }

        if (billDAO.billExists(
                appointment.getAppointmentId())) {

            return "A bill has already been generated for this appointment.";
        }

        return null;
    }


    public Bill generateBill(
            AppointmentDetails appointment) {

        String validationMessage =
                validateBilling(appointment);

        if (validationMessage != null) {
            return null;
        }

        double treatmentCost =
                appointment.getTreatmentCost();

        double consultationFee =
                appointment.getConsultationFee();

        double totalAmount =
                treatmentCost + consultationFee;


        Bill bill = new Bill(
                appointment.getAppointmentId(),
                treatmentCost,
                consultationFee,
                totalAmount
        );


        int billId =
                billDAO.createBill(bill);

        if (billId > 0) {

            bill.setBillId(billId);

            return bill;
        }

        return null;
    }
}