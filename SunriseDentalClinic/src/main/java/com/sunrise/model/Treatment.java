package com.sunrise.model;

public class Treatment {

    private int treatmentId;
    private String treatmentName;
    private double treatmentCost;
    private double consultationFee;

    public Treatment() {
    }

    public Treatment(int treatmentId, String treatmentName,
                     double treatmentCost, double consultationFee) {

        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.treatmentCost = treatmentCost;
        this.consultationFee = consultationFee;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getTotalCost() {
        return treatmentCost + consultationFee;
    }
}