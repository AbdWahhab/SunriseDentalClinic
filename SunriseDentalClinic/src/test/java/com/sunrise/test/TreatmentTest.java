package com.sunrise.test;

import com.sunrise.model.Treatment;

public class TreatmentTest {

    public static void main(String[] args) {

        Treatment treatment = new Treatment();

        treatment.setTreatmentCost(5000.00);
        treatment.setConsultationFee(1500.00);

        double expectedTotal = 6500.00;
        double actualTotal = treatment.getTotalCost();

        if (actualTotal == expectedTotal) {
            System.out.println("TEST PASSED: Treatment total calculation is correct.");
        } else {
            System.out.println("TEST FAILED: Expected " + expectedTotal
                    + " but got " + actualTotal);
        }
    }
}