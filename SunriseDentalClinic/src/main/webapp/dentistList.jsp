<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.sunrise.model.User" %>
<%@ page import="com.sunrise.model.Dentist" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Dentist> dentists =
        (List<Dentist>) request.getAttribute("dentists");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Dentist List - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<div class="page-container">

    <div class="page-header">

        <h1>Dentist List</h1>

        <p>
            View and search available dentists and their specializations.
        </p>

    </div>


    <div class="card">

        <div class="form-group">

            <label for="dentistSearch">
                Search Dentists
            </label>

            <input type="text"
                   id="dentistSearch"
                   placeholder="Search by dentist name or specialization..."
                   onkeyup="filterDentists()">

        </div>


        <div class="table-wrapper">

            <table id="dentistTable">

                <thead>

                    <tr>

                        <th>Dentist ID</th>
                        <th>Dentist Name</th>
                        <th>Specialization</th>

                    </tr>

                </thead>


                <tbody>

                    <%
                        if (dentists != null &&
                            !dentists.isEmpty()) {

                            for (Dentist dentist : dentists) {
                    %>

                    <tr>

                        <td>
                            <%= dentist.getDentistId() %>
                        </td>

                        <td>
                            <%= dentist.getDentistName() %>
                        </td>

                        <td>
                            <%= dentist.getSpecialization() %>
                        </td>

                    </tr>

                    <%
                            }

                        } else {
                    %>

                    <tr>

                        <td colspan="3"
                            class="text-center">

                            No dentists found.

                        </td>

                    </tr>

                    <%
                        }
                    %>

                </tbody>

            </table>

        </div>


        <div class="action-buttons mt-20">

            <a href="dashboard"
               class="btn btn-secondary">

                Back to Dashboard

            </a>

        </div>

    </div>

</div>


<script>

function filterDentists() {

    const input =
        document.getElementById("dentistSearch");

    const filter =
        input.value.toLowerCase();

    const table =
        document.getElementById("dentistTable");

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