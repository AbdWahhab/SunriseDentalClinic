<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.model.User" %>
<%@ page import="com.sunrise.model.Patient" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Patient> patients =
        (List<Patient>) request.getAttribute("patients");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Patient List - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Patient List</h1>

        <p>
            View and search registered patients.
        </p>

    </div>


    <div class="card">

        <div class="form-group">

            <label for="patientSearch">
                Search Patients
            </label>

            <input type="text"
                   id="patientSearch"
                   placeholder="Search by name, contact number or address..."
                   onkeyup="filterPatients()">

        </div>


        <div class="table-wrapper">

            <table id="patientTable">

                <thead>

                    <tr>

                        <th>Patient ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Contact Number</th>

                    </tr>

                </thead>


                <tbody>

                    <%
                        if (patients != null &&
                            !patients.isEmpty()) {

                            for (Patient patient : patients) {
                    %>

                    <tr>

                        <td>
                            <%= patient.getPatientId() %>
                        </td>

                        <td>
                            <%= patient.getPatientName() %>
                        </td>

                        <td>
                            <%= patient.getAddress() %>
                        </td>

                        <td>
                            <%= patient.getContactNumber() %>
                        </td>

                    </tr>

                    <%
                            }

                        } else {
                    %>

                    <tr>

                        <td colspan="4"
                            class="text-center">

                            No patients found.

                        </td>

                    </tr>

                    <%
                        }
                    %>

                </tbody>

            </table>

        </div>


        <div class="action-buttons mt-20">

            <a href="registerPatient.jsp"
               class="btn btn-primary">

                Register New Patient

            </a>


            <a href="dashboard"
               class="btn btn-secondary">

                Back to Dashboard

            </a>

        </div>

    </div>

</div>


<script>

function filterPatients() {

    const input =
        document.getElementById("patientSearch");

    const filter =
        input.value.toLowerCase();

    const table =
        document.getElementById("patientTable");

    const rows =
        table.getElementsByTagName("tbody")[0]
             .getElementsByTagName("tr");


    for (let i = 0; i < rows.length; i++) {

        const rowText =
            rows[i]
            .textContent
            .toLowerCase();

        if (rowText.indexOf(filter) > -1) {

            rows[i].style.display = "";

        } else {

            rows[i].style.display = "none";
        }
    }
}

</script>

</body>

</html>