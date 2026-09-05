<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">

    <title>Sunrise Dental Clinic - Login</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<div class="login-container">

    <div class="login-title">
        <h1>Sunrise Dental Clinic</h1>

        <p class="text-muted">
            Appointment Management System
        </p>
    </div>


    <div class="card">

        <h2>Staff Login</h2>

        <%
            String errorMessage =
                (String) request.getAttribute("errorMessage");

            if (errorMessage != null) {
        %>

        <div class="alert alert-error">
            <%= errorMessage %>
        </div>

        <%
            }
        %>


        <form action="login" method="post">

            <div class="form-group">

                <label>Username</label>

                <input type="text"
                       name="username"
                       placeholder="Enter username"
                       autocomplete="username"
                       required>

            </div>


            <div class="form-group">

                <label>Password</label>

                <input type="password"
                       name="password"
                       placeholder="Enter password"
                       autocomplete="current-password"
                       required>

            </div>


            <button type="submit"
                    class="btn btn-primary"
                    style="width:100%;">

                Login

            </button>

        </form>

    </div>

</div>

</body>

</html>