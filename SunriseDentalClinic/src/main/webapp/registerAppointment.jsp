<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.model.User" %>
<%@ page import="com.sunrise.model.Dentist" %>
<%@ page import="com.sunrise.model.Treatment" %>
<%@ page import="com.sunrise.model.Patient" %>
<%@ page import="com.sunrise.dao.DentistDAO" %>
<%@ page import="com.sunrise.dao.TreatmentDAO" %>
<%@ page import="com.sunrise.dao.PatientDAO" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    PatientDAO patientDAO =
        new PatientDAO();

    DentistDAO dentistDAO =
        new DentistDAO();

    TreatmentDAO treatmentDAO =
        new TreatmentDAO();

    List<Patient> patients =
        patientDAO.getAllPatients();

    List<Dentist> dentists =
        dentistDAO.getAllDentists();

    List<Treatment> treatments =
        treatmentDAO.getAllTreatments();

    String successMessage =
        (String) request.getAttribute("successMessage");

    String errorMessage =
        (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Register Appointment - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Register New Appointment</h1>

        <p>
            Select the patient, dentist, treatment, date and time.
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


        <form action="registerAppointment"
              method="post"
              id="appointmentForm">


            <div class="form-group">

                <label for="patientSearch">
                    Patient
                </label>

                <input type="text"
                       id="patientSearch"
                       list="patientList"
                       placeholder="Type patient name or contact number"
                       autocomplete="off"
                       required>

                <input type="hidden"
                       id="patientId"
                       name="patientId">


                <datalist id="patientList">

                    <% for (Patient patient : patients) { %>

                        <option
                            value="<%= patient.getPatientName() %> - <%= patient.getContactNumber() %>"
                            data-id="<%= patient.getPatientId() %>">
                        </option>

                    <% } %>

                </datalist>


                <div class="mt-20">

                    <a href="registerPatient.jsp"
                       class="btn btn-secondary">

                        Patient not found? Register New Patient

                    </a>

                </div>

            </div>


            <div class="form-group">

                <label for="dentistId">
                    Dentist
                </label>

                <select name="dentistId"
                        id="dentistId"
                        required>

                    <option value="">
                        Select Dentist
                    </option>

                    <% for (Dentist dentist : dentists) { %>

                        <option value="<%= dentist.getDentistId() %>">

                            <%= dentist.getDentistName() %>
                            -
                            <%= dentist.getSpecialization() %>

                        </option>

                    <% } %>

                </select>

            </div>


            <div class="form-group">

                <label for="treatmentId">
                    Treatment
                </label>

                <select name="treatmentId"
                        id="treatmentId"
                        required>

                    <option value="">
                        Select Treatment
                    </option>

                    <% for (Treatment treatment : treatments) { %>

                        <option value="<%= treatment.getTreatmentId() %>">

                            <%= treatment.getTreatmentName() %>
                            -
                            LKR
                            <%= String.format(
                                    "%.2f",
                                    treatment.getTreatmentCost()
                                ) %>

                        </option>

                    <% } %>

                </select>

            </div>


            <div class="form-group">

                <label for="appointmentDate">
                    Appointment Date
                </label>

                <input type="date"
                       name="appointmentDate"
                       id="appointmentDate"
                       required>

            </div>


            <div class="form-group">

                <label for="appointmentTime">
                    Appointment Time
                </label>

                <input type="time"
                       name="appointmentTime"
                       id="appointmentTime"
                       required>

            </div>


            <div class="action-buttons">

                <button type="submit"
                        class="btn btn-primary">

                    Register Appointment

                </button>


                <a href="appointments"
                   class="btn btn-secondary">

                    View Appointments

                </a>


                <a href="dashboard"
                   class="btn btn-secondary">

                    Back to Dashboard

                </a>

            </div>

        </form>

    </div>

</div>


<script>

const patientSearch =
    document.getElementById("patientSearch");

const patientId =
    document.getElementById("patientId");

const patientList =
    document.getElementById("patientList");


patientSearch.addEventListener(
    "input",
    function () {

        patientId.value = "";

        const options =
            patientList.options;

        for (
            let i = 0;
            i < options.length;
            i++
        ) {

            if (
                options[i].value ===
                patientSearch.value
            ) {

                patientId.value =
                    options[i]
                    .getAttribute("data-id");

                break;
            }
        }
    }
);


document
    .getElementById("appointmentForm")
    .addEventListener(
        "submit",
        function (event) {

            if (patientId.value === "") {

                event.preventDefault();

                alert(
                    "Please select a valid patient from the search list."
                );

                patientSearch.focus();
            }
        }
    );


const appointmentDate =
    document.getElementById("appointmentDate");

const today =
    new Date()
    .toISOString()
    .split("T")[0];

appointmentDate.setAttribute(
    "min",
    today
);

</script>

</body>

</html>