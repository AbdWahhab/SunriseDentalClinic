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

    String successMessage =
        (String) request.getAttribute("successMessage");

    String errorMessage =
        (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Register Patient - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Register New Patient</h1>

        <p>
            Enter the patient details below.
        </p>

    </div>


    <div class="card">

        <% if (successMessage != null) { %>

            <div class="alert alert-success">
                <%= successMessage %>
            </div>

        <% } %>


        <% if (errorMessage != null) { %>

            <div class="alert alert-error">
                <%= errorMessage %>
            </div>

        <% } %>


        <form action="registerPatient"
              method="post">

            <div class="form-group">

                <label for="patientName">
                    Patient Name
                </label>

                <input type="text"
                       id="patientName"
                       name="patientName"
                       placeholder="Enter full name"
                       maxlength="100"
                       required>

            </div>


            <div class="form-group">

                <label for="address">
                    Address
                </label>

                <textarea id="address"
                          name="address"
                          placeholder="Enter patient address"
                          maxlength="255"
                          required></textarea>

            </div>


            <div class="form-group">

                <label for="contactNumber">
                    Contact Number
                </label>

                <input type="tel"
                       id="contactNumber"
                       name="contactNumber"
                       placeholder="Example: 0771234567"
                       maxlength="15"
                       pattern="[0-9+\-\s]{9,15}"
                       title="Enter a valid contact number"
                       required>

            </div>


            <div class="action-buttons">

                <button type="submit"
                        class="btn btn-primary">

                    Register Patient

                </button>


                <a href="patients"
                   class="btn btn-secondary">

                    View Patients

                </a>


                <a href="dashboard"
                   class="btn btn-secondary">

                    Back to Dashboard

                </a>

            </div>

        </form>

    </div>

</div>

</body>

</html>