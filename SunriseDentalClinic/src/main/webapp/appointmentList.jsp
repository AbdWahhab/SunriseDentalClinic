<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.model.User" %>
<%@ page import="com.sunrise.model.AppointmentDetails" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<AppointmentDetails> appointments =
        (List<AppointmentDetails>) request.getAttribute("appointments");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Appointment List - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Appointment List</h1>

        <p>
            Search, filter and manage registered appointments.
        </p>

    </div>


    <div class="card">

        <h2>Appointment Filters</h2>

        <div class="form-group">

            <label for="searchText">
                Search
            </label>

            <input type="text"
                   id="searchText"
                   placeholder="Search appointment, patient, dentist or treatment..."
                   onkeyup="filterAppointments()">

        </div>


        <div class="form-group">

            <label for="dateFilter">
                Appointment Date
            </label>

            <input type="date"
                   id="dateFilter"
                   onchange="filterAppointments()">

        </div>


        <div class="form-group">

            <label for="statusFilter">
                Status
            </label>

            <select id="statusFilter"
                    onchange="filterAppointments()">

                <option value="">
                    All Statuses
                </option>

                <option value="SCHEDULED">
                    Scheduled
                </option>

                <option value="COMPLETED">
                    Completed
                </option>

                <option value="CANCELLED">
                    Cancelled
                </option>

            </select>

        </div>

    </div>


    <div class="card">

        <div class="table-wrapper">

            <table id="appointmentTable">

                <thead>

                    <tr>
                        <th>Appointment No.</th>
                        <th>Patient</th>
                        <th>Contact</th>
                        <th>Dentist</th>
                        <th>Treatment</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>

                </thead>


                <tbody>

                <%
                    if (appointments != null &&
                        !appointments.isEmpty()) {

                        for (AppointmentDetails appointment : appointments) {
                %>

                    <tr>

                        <td>
                            <%= appointment.getAppointmentNumber() %>
                        </td>

                        <td>
                            <%= appointment.getPatientName() %>
                        </td>

                        <td>
                            <%= appointment.getContactNumber() %>
                        </td>

                        <td>
                            <%= appointment.getDentistName() %>
                        </td>

                        <td>
                            <%= appointment.getTreatmentName() %>
                        </td>

                        <td class="appointmentDate">
                            <%= appointment.getAppointmentDate() %>
                        </td>

                        <td>
                            <%= appointment.getAppointmentTime() %>
                        </td>

                        <td class="appointmentStatus">
                            <%= appointment.getStatus() %>
                        </td>

                        <td>

                            <div class="action-buttons">

                                <a href="appointmentDetails?id=<%= appointment.getAppointmentId() %>"
                                   class="btn btn-secondary">

                                    View

                                </a>


                                <% if ("SCHEDULED".equals(appointment.getStatus())) { %>

                                    <form action="appointmentStatus"
                                          method="post">

                                        <input type="hidden"
                                               name="appointmentId"
                                               value="<%= appointment.getAppointmentId() %>">

                                        <input type="hidden"
                                               name="status"
                                               value="COMPLETED">

                                        <button type="submit"
                                                class="btn btn-success"
                                                onclick="return confirm('Mark this appointment as completed?');">

                                            Complete

                                        </button>

                                    </form>


                                    <form action="appointmentStatus"
                                          method="post">

                                        <input type="hidden"
                                               name="appointmentId"
                                               value="<%= appointment.getAppointmentId() %>">

                                        <input type="hidden"
                                               name="status"
                                               value="CANCELLED">

                                        <button type="submit"
                                                class="btn btn-danger"
                                                onclick="return confirm('Cancel this appointment?');">

                                            Cancel

                                        </button>

                                    </form>

                                <% } %>

                            </div>

                        </td>

                    </tr>

                <%
                        }

                    } else {
                %>

                    <tr>

                        <td colspan="9"
                            class="text-center">

                            No appointments found.

                        </td>

                    </tr>

                <%
                    }
                %>

                </tbody>

            </table>

        </div>


        <div class="action-buttons mt-20">

            <a href="registerAppointment.jsp"
               class="btn btn-primary">

                Register New Appointment

            </a>


            <a href="dashboard"
               class="btn btn-secondary">

                Back to Dashboard

            </a>

        </div>

    </div>

</div>


<script>

function filterAppointments() {

    const searchText =
        document
        .getElementById("searchText")
        .value
        .toLowerCase();

    const dateFilter =
        document
        .getElementById("dateFilter")
        .value;

    const statusFilter =
        document
        .getElementById("statusFilter")
        .value
        .toLowerCase();

    const table =
        document.getElementById("appointmentTable");

    const rows =
        table.getElementsByTagName("tbody")[0]
             .getElementsByTagName("tr");


    for (let i = 0; i < rows.length; i++) {

        const row = rows[i];

        const dateCell =
            row.querySelector(".appointmentDate");

        const statusCell =
            row.querySelector(".appointmentStatus");


        if (!dateCell || !statusCell) {
            continue;
        }


        const rowText =
            row.textContent.toLowerCase();

        const rowDate =
            dateCell.textContent.trim();

        const rowStatus =
            statusCell
            .textContent
            .trim()
            .toLowerCase();


        const matchesText =
            rowText.includes(searchText);

        const matchesDate =
            dateFilter === "" ||
            rowDate === dateFilter;

        const matchesStatus =
            statusFilter === "" ||
            rowStatus === statusFilter;


        if (
            matchesText &&
            matchesDate &&
            matchesStatus
        ) {

            row.style.display = "";

        } else {

            row.style.display = "none";
        }
    }
}

</script>

</body>

</html>