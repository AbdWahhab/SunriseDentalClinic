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

    Integer totalPatients =
        (Integer) request.getAttribute("totalPatients");

    Integer totalDentists =
        (Integer) request.getAttribute("totalDentists");

    Integer totalAppointments =
        (Integer) request.getAttribute("totalAppointments");

    Integer scheduledAppointments =
        (Integer) request.getAttribute("scheduledAppointments");

    Integer completedAppointments =
        (Integer) request.getAttribute("completedAppointments");

    Integer cancelledAppointments =
        (Integer) request.getAttribute("cancelledAppointments");

    Double totalRevenue =
        (Double) request.getAttribute("totalRevenue");

    if (totalPatients == null) totalPatients = 0;
    if (totalDentists == null) totalDentists = 0;
    if (totalAppointments == null) totalAppointments = 0;
    if (scheduledAppointments == null) scheduledAppointments = 0;
    if (completedAppointments == null) completedAppointments = 0;
    if (cancelledAppointments == null) cancelledAppointments = 0;
    if (totalRevenue == null) totalRevenue = 0.0;
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Dashboard - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Sunrise Dental Clinic</h1>

        <p>
            Welcome,
            <strong><%= loggedUser.getFullName() %></strong>
            |
            Role:
            <strong><%= loggedUser.getRole() %></strong>
        </p>

    </div>


    <h2>Dashboard Summary</h2>

    <div class="stats-grid">

        <div class="stat-card">
            <h3>Total Patients</h3>
            <div class="stat-value">
                <%= totalPatients %>
            </div>
        </div>


        <div class="stat-card">
            <h3>Total Dentists</h3>
            <div class="stat-value">
                <%= totalDentists %>
            </div>
        </div>


        <div class="stat-card">
            <h3>Total Appointments</h3>
            <div class="stat-value">
                <%= totalAppointments %>
            </div>
        </div>


        <div class="stat-card">
            <h3>Scheduled</h3>
            <div class="stat-value">
                <%= scheduledAppointments %>
            </div>
        </div>


        <div class="stat-card">
            <h3>Completed</h3>
            <div class="stat-value">
                <%= completedAppointments %>
            </div>
        </div>


        <div class="stat-card">
            <h3>Cancelled</h3>
            <div class="stat-value">
                <%= cancelledAppointments %>
            </div>
        </div>


        <div class="stat-card">
            <h3>Total Revenue</h3>

            <div class="stat-value"
                 style="font-size: 22px;">

                LKR
                <%= String.format("%.2f", totalRevenue) %>

            </div>
        </div>

    </div>


    <h2>System Menu</h2>

    <div class="menu-grid">


        <div class="menu-card">

            <h3>Patients</h3>

            <a href="registerPatient.jsp"
               class="btn btn-primary">
                Register New Patient
            </a>

            <a href="patients"
               class="btn btn-secondary">
                View Patients
            </a>

        </div>


        <div class="menu-card">

            <h3>Appointments</h3>

            <a href="registerAppointment.jsp"
               class="btn btn-primary">
                Register Appointment
            </a>

            <a href="appointments"
               class="btn btn-secondary">
                View Appointments
            </a>

        </div>


        <div class="menu-card">

            <h3>Dentists</h3>

            <a href="dentists"
               class="btn btn-secondary">
                View Dentists
            </a>

        </div>


        <div class="menu-card">

            <h3>Billing</h3>

            <a href="bill.jsp"
               class="btn btn-primary">
                Calculate / Print Bill
            </a>

        </div>


        <div class="menu-card">

            <h3>Reports</h3>

            <a href="reports"
               class="btn btn-secondary">
                View Reports
            </a>

        </div>


        <div class="menu-card">

            <h3>Help</h3>

            <a href="help.jsp"
               class="btn btn-secondary">
                Help & Guidance
            </a>

        </div>

    </div>


    <div class="mt-20">

        <a href="logout"
           class="btn btn-danger"
           onclick="return confirm('Are you sure you want to logout?');">

            Logout

        </a>

    </div>

</div>

</body>

</html>