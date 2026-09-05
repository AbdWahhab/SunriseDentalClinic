# Sunrise Dental Clinic - Test Plan

## 1. Purpose

This test plan is used to verify that the Sunrise Dental Clinic Appointment System works correctly and meets the main functional requirements of the assessment.

The testing process includes:

- Functional testing
- Validation testing
- Database testing
- Billing testing
- Session testing
- Automated testing

## 2. Test Environment

The system is tested using:

- Java
- Apache Tomcat 10.1
- MySQL Server 8.0
- Eclipse IDE
- Google Chrome
- Localhost deployment

Application URL:

http://localhost:8081/SunriseDentalClinic/

## 3. Test Cases

| Test ID | Module | Test Scenario | Test Data | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|
| TC01 | Login | Login with valid username and password | Valid admin credentials | User should be redirected to the dashboard | User redirected to dashboard successfully | Pass |
| TC02 | Login | Login with invalid password | Incorrect password | Login should be rejected and an error message displayed | Login rejected correctly | Pass |
| TC03 | Session | Access dashboard without login | No active session | User should be redirected to login page | User redirected to login page | Pass |
| TC04 | Patient | Register a valid patient | Name: Jason, Address: Kandy, Contact: 0771234567 | Patient should be saved successfully | Patient registered successfully | Pass |
| TC05 | Patient | Register patient with invalid contact number | Contact: 12345 | Registration should be rejected | "Contact number must contain exactly 10 digits." displayed | Pass |
| TC06 | Patient | Register patient with empty required field | Empty patient name | Registration should be rejected | Required field validation displayed | Pass |
| TC07 | Appointment | Register valid appointment | Valid patient, dentist, treatment, date and time | Appointment should be registered with automatic appointment number | Appointment created successfully | Pass |
| TC08 | Appointment | Attempt dentist double booking | Same dentist, date and time | Second appointment should be rejected | Double booking prevented | Pass |
| TC09 | Appointment | Search valid appointment number | Existing appointment number | Appointment details should be displayed | Appointment details displayed correctly | Pass |
| TC10 | Appointment | Search invalid appointment number | Non-existing number | Appointment should not be found | Appropriate message displayed | Pass |
| TC11 | Appointment | Complete scheduled appointment | Scheduled appointment | Status should change to COMPLETED | Status changed successfully | Pass |
| TC12 | Appointment | Cancel scheduled appointment | Scheduled appointment | Status should change to CANCELLED | Status changed successfully | Pass |
| TC13 | Billing | Generate bill for valid appointment | Valid scheduled/completed appointment | Treatment cost and consultation fee should be added | Bill generated correctly | Pass |
| TC14 | Billing | Generate duplicate bill | Appointment already billed | Second bill should be rejected | Duplicate billing prevented | Pass |
| TC15 | Billing | Generate bill for cancelled appointment | Cancelled appointment | Billing should be rejected | Cancelled appointment billing prevented | Pass |
| TC16 | Reports | Open reports page | Existing database records | Report statistics should be displayed | Reports displayed correctly | Pass |
| TC17 | API | Request valid appointment through API | Existing appointment number | JSON appointment data should be returned | JSON returned successfully | Pass |
| TC18 | API | Request dashboard API | GET /api/dashboard | JSON summary should be returned | Dashboard JSON returned successfully | Pass |
| TC19 | Automated Test | Treatment total calculation | Treatment cost 5000, consultation fee 1500 | Total should equal 6500 | Automated test passed | Pass |
| TC20 | Automated Test | Appointment data validation | Valid patient, dentist, treatment, date, time and status | Appointment data should pass validation | Automated test passed | Pass |
| TC21 | ANT Build | Run full ANT build | build target | Source should compile, tests should run and WAR should be generated | BUILD SUCCESSFUL | Pass |

## 4. Automated Testing

The project contains automated Java test classes under:

src/test/java/com/sunrise/test

Current automated test classes:

- TreatmentTest
- AppointmentValidationTest
- AllTests

The `AllTests` class executes the automated test cases together.

Example successful output:

===== SUNRISE DENTAL CLINIC AUTOMATED TESTS =====

Running TreatmentTest...

TEST PASSED: Treatment total calculation is correct.

Running AppointmentValidationTest...

TEST PASSED: Appointment data validation is correct.

===== TEST EXECUTION COMPLETED =====

## 5. ANT Test Execution

The automated tests can also be executed through the ANT build configuration.

The ANT build performs:

1. Cleaning previous build files
2. Compiling main Java source files
3. Compiling test source files
4. Running automated tests
5. Creating the WAR deployment file

A successful execution produces:

BUILD SUCCESSFUL

The generated WAR file is created at:

dist/SunriseDentalClinic.war

## 6. Test Evidence

Screenshots should be captured for important test cases and included in the final assessment report.

Recommended evidence includes:

- Successful login
- Invalid login
- Patient registration
- Invalid patient contact number
- Appointment registration
- Dentist double-booking prevention
- Appointment search
- Appointment status update
- Successful bill generation
- Duplicate bill prevention
- Cancelled appointment billing validation
- Reports page
- Appointment API JSON response
- Dashboard API JSON response
- Automated test console output
- ANT BUILD SUCCESSFUL output

## 7. Testing Conclusion

The completed tests demonstrate that the main functions of the Sunrise Dental Clinic Appointment System operate correctly.

The system includes validation at both client and server level, database-backed functionality, business-rule validation, automated tests, and ANT build automation.