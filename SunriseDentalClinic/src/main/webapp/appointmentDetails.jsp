<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.model.User" %>
<%@ page import="com.sunrise.model.AppointmentDetails" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    AppointmentDetails appointment =
        (AppointmentDetails) request.getAttribute("appointment");

    if (appointment == null) {
        response.sendRedirect("appointments");
        return;
    }
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Appointment Details - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Appointment Details</h1>

        <p>
            Complete information for appointment
            <strong><%= appointment.getAppointmentNumber() %></strong>
        </p>

    </div>


    <div class="card">

        <div class="table-wrapper">

            <table>

                <tbody>

                    <tr>
                        <th>Appointment Number</th>
                        <td>
                            <%= appointment.getAppointmentNumber() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Patient Name</th>
                        <td>
                            <%= appointment.getPatientName() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Address</th>
                        <td>
                            <%= appointment.getAddress() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Contact Number</th>
                        <td>
                            <%= appointment.getContactNumber() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Dentist</th>
                        <td>
                            <%= appointment.getDentistName() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Specialization</th>
                        <td>
                            <%= appointment.getSpecialization() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Treatment</th>
                        <td>
                            <%= appointment.getTreatmentName() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Appointment Date</th>
                        <td>
                            <%= appointment.getAppointmentDate() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Appointment Time</th>
                        <td>
                            <%= appointment.getAppointmentTime() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Status</th>
                        <td>
                            <strong>
                                <%= appointment.getStatus() %>
                            </strong>
                        </td>
                    </tr>

                    <tr>
                        <th>Treatment Cost</th>
                        <td>
                            LKR
                            <%= String.format(
                                    "%.2f",
                                    appointment.getTreatmentCost()
                                ) %>
                        </td>
                    </tr>

                    <tr>
                        <th>Consultation Fee</th>
                        <td>
                            LKR
                            <%= String.format(
                                    "%.2f",
                                    appointment.getConsultationFee()
                                ) %>
                        </td>
                    </tr>

                    <tr>
                        <th>Total Amount</th>
                        <td>
                            <strong>
                                LKR
                                <%= String.format(
                                        "%.2f",
                                        appointment.getTotalCost()
                                    ) %>
                            </strong>
                        </td>
                    </tr>

                </tbody>

            </table>

        </div>


        <div class="action-buttons mt-20">

            <a href="billing?appointmentNumber=<%= appointment.getAppointmentNumber() %>"
               class="btn btn-primary">

                Generate / View Bill

            </a>


            <a href="appointments"
               class="btn btn-secondary">

                Back to Appointment List

            </a>


            <a href="dashboard"
               class="btn btn-secondary">

                Dashboard

            </a>

        </div>

    </div>

</div>

</body>

</html>