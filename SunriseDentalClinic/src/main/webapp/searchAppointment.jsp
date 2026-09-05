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

    String errorMessage =
        (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Search Appointment - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Search Appointment</h1>

        <p>
            Enter an appointment number to view its details.
        </p>

    </div>


    <div class="card">

        <form action="searchAppointment"
              method="get">

            <div class="form-group">

                <label for="appointmentNumber">
                    Appointment Number
                </label>

                <input type="text"
                       id="appointmentNumber"
                       name="appointmentNumber"
                       placeholder="Enter appointment number"
                       required>

            </div>


            <div class="action-buttons">

                <button type="submit"
                        class="btn btn-primary">

                    Search Appointment

                </button>


                <a href="appointments"
                   class="btn btn-secondary">

                    View All Appointments

                </a>


                <a href="dashboard"
                   class="btn btn-secondary">

                    Back to Dashboard

                </a>

            </div>

        </form>

    </div>


    <% if (errorMessage != null) { %>

        <div class="alert alert-error">
            <%= errorMessage %>
        </div>

    <% } %>


    <% if (appointment != null) { %>

        <div class="card">

            <div class="page-header">

                <h2>Appointment Details</h2>

                <p>
                    Details for appointment
                    <strong>
                        <%= appointment.getAppointmentNumber() %>
                    </strong>
                </p>

            </div>


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

                    </tbody>

                </table>

            </div>


            <div class="action-buttons mt-20">

                <a href="appointmentDetails?id=<%= appointment.getAppointmentId() %>"
                   class="btn btn-primary">

                    View Full Details

                </a>


                <a href="billing?appointmentNumber=<%= appointment.getAppointmentNumber() %>"
                   class="btn btn-success">

                    Billing

                </a>

            </div>

        </div>

    <% } %>

</div>

</body>

</html>