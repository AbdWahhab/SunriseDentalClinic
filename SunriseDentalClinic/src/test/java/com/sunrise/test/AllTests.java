package com.sunrise.test;

public class AllTests {

    public static void main(String[] args) {

        System.out.println("===== SUNRISE DENTAL CLINIC AUTOMATED TESTS =====");
        System.out.println();

        System.out.println("Running TreatmentTest...");
        TreatmentTest.main(args);

        System.out.println();

        System.out.println("Running AppointmentValidationTest...");
        AppointmentValidationTest.main(args);

        System.out.println();
        System.out.println("===== TEST EXECUTION COMPLETED =====");
    }
}