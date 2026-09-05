<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.model.User" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Help - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Help</h1>

        <p>
            Guidance for using the Sunrise Dental Clinic Appointment System.
        </p>

    </div>


    <div class="card">

        <h2>About the System</h2>

        <p>
            This system helps clinic staff manage patients,
            appointments, dentist schedules and billing.
        </p>

    </div>


    <div class="card">

        <h2>1. Register New Patient</h2>

        <p>
            Use the patient registration option to enter a new
            patient's name, address and contact number.
        </p>

        <p>
            A patient should be registered before creating an appointment.
        </p>

    </div>


    <div class="card">

        <h2>2. Register Appointment</h2>

        <p>
            Open the appointment registration page and search for
            the required patient using the patient's name or contact number.
        </p>

        <p>
            Select the dentist, treatment type, appointment date
            and appointment time.
        </p>

        <p>
            The appointment number is generated automatically by the system.
        </p>

        <p>
            The system also prevents the same dentist from being
            booked twice for the same date and time.
        </p>

    </div>


    <div class="card">

        <h2>3. View and Search Appointments</h2>

        <p>
            Use the appointment list to view all registered appointments.
        </p>

        <p>
            You can search using appointment information such as
            appointment number, patient, dentist or treatment.
        </p>

        <p>
            Appointments can also be filtered by date and status.
        </p>

    </div>


    <div class="card">

        <h2>4. Appointment Status</h2>

        <p>
            A scheduled appointment can be marked as
            <strong>Completed</strong> when the treatment has finished.
        </p>

        <p>
            It can also be marked as
            <strong>Cancelled</strong> when the appointment is cancelled.
        </p>

    </div>


    <div class="card">

        <h2>5. Calculate and Print Bill</h2>

        <p>
            Open the billing page and enter the appointment number.
        </p>

        <p>
            The system automatically calculates the bill using:
        </p>

        <p>
            <strong>
                Treatment Cost + Consultation Fee = Total Amount
            </strong>
        </p>

        <p>
            After the bill is generated, the receipt can be printed.
        </p>

        <p>
            The system prevents duplicate bills from being generated
            for the same appointment.
        </p>

    </div>


    <div class="card">

        <h2>6. Reports</h2>

        <p>
            The reports section displays information such as
            total bills, total revenue and average bill amount.
        </p>

        <p>
            It also shows appointment totals grouped by dentist
            and treatment type.
        </p>

        <p>
            Reports can be printed when required.
        </p>

    </div>


    <div class="card">

        <h2>7. Logout</h2>

        <p>
            Use the Logout option when you have finished using the system.
        </p>

        <p>
            Logging out ends the current user session and returns
            you to the login page.
        </p>

    </div>


    <div class="action-buttons">

        <a href="dashboard"
           class="btn btn-secondary">

            Back to Dashboard

        </a>

    </div>

</div>

</body>

</html>