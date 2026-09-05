<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.sunrise.model.User" %>
<%@ page import="com.sunrise.model.Bill" %>
<%@ page import="com.sunrise.model.AppointmentDetails" %>

<%
    User loggedUser =
        (User) session.getAttribute("loggedUser");

    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Bill bill =
        (Bill) request.getAttribute("bill");

    AppointmentDetails appointment =
        (AppointmentDetails) request.getAttribute("appointment");

    String successMessage =
        (String) request.getAttribute("successMessage");

    String errorMessage =
        (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Billing - Sunrise Dental Clinic</title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">

    <style>

        .receipt {
            max-width: 600px;
            margin: 20px auto;
            background: white;
            border: 1px solid #d1d5db;
            border-radius: 10px;
            padding: 30px;
        }

        .receipt-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .receipt-header h2 {
            margin-bottom: 5px;
            color: #16324f;
        }

        .receipt-row {
            display: flex;
            justify-content: space-between;
            gap: 20px;
            padding: 9px 0;
        }

        .receipt-divider {
            border: none;
            border-top: 1px solid #d1d5db;
            margin: 15px 0;
        }

        .receipt-total {
            font-size: 18px;
            font-weight: bold;
            color: #16324f;
        }

        .receipt-footer {
            text-align: center;
            margin-top: 20px;
            color: #6b7280;
        }

        @media print {

            .no-print {
                display: none !important;
            }

            body {
                background: white;
            }

            .page-container {
                width: 100%;
                margin: 0;
            }

            .receipt {
                border: none;
                box-shadow: none;
                max-width: 100%;
                margin: 0;
            }
        }

    </style>

</head>

<body>

<div class="page-container">

    <div class="page-header no-print">

        <h1>Appointment Billing</h1>

        <p>
            Search an appointment and generate the patient's bill.
        </p>

    </div>


    <div class="card no-print">

        <form action="billing"
              method="get">

            <div class="form-group">

                <label for="appointmentNumber">
                    Appointment Number
                </label>

                <input type="text"
       id="appointmentNumber"
       name="appointmentNumber"
       placeholder="Enter appointment number"
       value="<%= request.getParameter("appointmentNumber") != null
               ? request.getParameter("appointmentNumber")
               : "" %>"
       maxlength="30"
       required>

            </div>

            <div class="action-buttons">

                <button type="submit"
                        class="btn btn-primary">

                    Search Appointment

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


    <% if (errorMessage != null) { %>

        <div class="alert alert-error no-print">
            <%= errorMessage %>
        </div>

    <% } %>


    <% if (successMessage != null) { %>

        <div class="alert alert-success no-print">
            <%= successMessage %>
        </div>

    <% } %>


    <%
        if (appointment != null && bill == null) {
    %>

    <div class="card no-print">

        <div class="page-header">

            <h2>Appointment Found</h2>

            <p>
                Review the appointment details before generating the bill.
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
                        <th>Patient</th>
                        <td>
                            <%= appointment.getPatientName() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Dentist</th>
                        <td>
                            <%= appointment.getDentistName() %>
                        </td>
                    </tr>

                    <tr>
                        <th>Treatment</th>
                        <td>
                            <%= appointment.getTreatmentName() %>
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

            <form action="billing"
                  method="post">

                <input type="hidden"
                       name="appointmentNumber"
                       value="<%= appointment.getAppointmentNumber() %>">

                <button type="submit"
                        class="btn btn-success"
                        onclick="return confirm('Generate bill for this appointment?');">

                    Generate Bill

                </button>

            </form>

        </div>

    </div>

    <%
        }
    %>


    <%
        if (bill != null && appointment != null) {
    %>

    <div class="receipt">

        <div class="receipt-header">

            <h2>Sunrise Dental Clinic</h2>

            <p>Colombo, Sri Lanka</p>

            <p>
                Payment Receipt
            </p>

        </div>


        <hr class="receipt-divider">


        <div class="receipt-row">

            <span>
                Bill Number
            </span>

            <strong>
                <%= bill.getBillId() %>
            </strong>

        </div>


        <div class="receipt-row">

            <span>
                Appointment Number
            </span>

            <span>
                <%= appointment.getAppointmentNumber() %>
            </span>

        </div>


        <div class="receipt-row">

            <span>
                Patient
            </span>

            <span>
                <%= appointment.getPatientName() %>
            </span>

        </div>


        <div class="receipt-row">

            <span>
                Dentist
            </span>

            <span>
                <%= appointment.getDentistName() %>
            </span>

        </div>


        <div class="receipt-row">

            <span>
                Treatment
            </span>

            <span>
                <%= appointment.getTreatmentName() %>
            </span>

        </div>


        <hr class="receipt-divider">


        <div class="receipt-row">

            <span>
                Treatment Cost
            </span>

            <span>
                LKR
                <%= String.format(
                        "%.2f",
                        bill.getTreatmentCost()
                    ) %>
            </span>

        </div>


        <div class="receipt-row">

            <span>
                Consultation Fee
            </span>

            <span>
                LKR
                <%= String.format(
                        "%.2f",
                        bill.getConsultationFee()
                    ) %>
            </span>

        </div>


        <hr class="receipt-divider">


        <div class="receipt-row receipt-total">

            <span>
                Total Amount
            </span>

            <span>
                LKR
                <%= String.format(
                        "%.2f",
                        bill.getTotalAmount()
                    ) %>
            </span>

        </div>


        <div class="receipt-footer">

            Thank you for choosing Sunrise Dental Clinic.

        </div>

    </div>


    <div class="action-buttons no-print">

        <button type="button"
                class="btn btn-primary"
                onclick="window.print()">

            Print Receipt

        </button>

        <a href="billing"
           class="btn btn-secondary">

            New Billing Search

        </a>

        <a href="dashboard"
           class="btn btn-secondary">

            Back to Dashboard

        </a>

    </div>

    <%
        }
    %>

</div>

</body>

</html>