# Sunrise Dental Clinic Appointment System

A Java web-based appointment and billing management system developed for Sunrise Dental Clinic in Colombo, Sri Lanka.

This project was developed as part of the CIS6003 Advanced Programming assessment.

## Project Overview

The Sunrise Dental Clinic Appointment System is designed to help clinic staff manage patients, dentists, appointments, billing, and reports through a web-based interface.

The system replaces manual appointment handling with a structured database-driven application.

## Main Features

- User authentication and session management
- Patient registration
- Patient search and listing
- Dentist listing
- Appointment registration
- Automatic appointment number generation
- Dentist double-booking prevention
- Appointment search
- Appointment filtering by date and status
- Appointment status management
- Treatment and consultation fee calculation
- Bill generation
- Duplicate billing prevention
- Printable patient receipts
- Dashboard statistics
- Clinic reports
- Printable reports
- Help page
- Logout and session termination
- REST-style JSON web service endpoints

## Technologies Used

- Java
- Jakarta Servlets
- JSP
- JDBC
- MySQL
- HTML
- CSS
- JavaScript
- Apache Tomcat 10.1
- Eclipse IDE
- Git and GitHub

## Application Architecture

The project follows a layered architecture.

### 1. Presentation Layer

- JSP
- HTML
- CSS
- JavaScript

### 2. Controller Layer

- Jakarta Servlets

### 3. Service Layer

- Business logic
- Application validation

### 4. Data Access Layer

- DAO classes
- JDBC

### 5. Database Layer

- MySQL

The project also applies design approaches including MVC, DAO, and Service Layer patterns.

## Core Modules

### Authentication

Users must log in before accessing protected areas of the application.

Sessions are used to maintain authenticated user access. Users can log out to terminate their current session.

### Patient Management

Clinic staff can register new patients by entering their name, address, and contact number.

Registered patients can be viewed and searched using the patient listing page.

### Dentist Management

The system maintains dentist information including dentist name and specialization.

Clinic staff can view and search the available dentists.

### Appointment Management

Appointments can be created by selecting:

- A registered patient
- Dentist
- Treatment
- Appointment date
- Appointment time

Appointment numbers are generated automatically by the system.

The system checks dentist availability to prevent the same dentist from being booked twice for the same date and time.

Appointments can have the following statuses:

- SCHEDULED
- COMPLETED
- CANCELLED

Clinic staff can view appointment details and update scheduled appointments as completed or cancelled.

Appointments can also be searched and filtered by appointment information, date, and status.

### Billing

The billing module allows clinic staff to search for an appointment using its appointment number.

Bills are calculated using:

**Treatment Cost + Consultation Fee = Total Amount**

The system prevents:

- Duplicate bills for the same appointment
- Billing of cancelled appointments

After successful bill generation, a receipt is displayed and can be printed.

### Dashboard

The dashboard provides an overview of important clinic information.

It displays statistics such as:

- Total patients
- Total dentists
- Total appointments
- Scheduled appointments
- Completed appointments
- Cancelled appointments
- Total revenue

The dashboard also provides navigation to the major functions of the application.

### Reports

The reporting module provides information including:

- Total bills
- Total revenue
- Average bill amount
- Appointments by dentist
- Appointments by treatment

Reports can also be printed directly from the application.

### Help

The Help page provides guidance for clinic staff on using the main functions of the system.

## Web Service Endpoints

The project includes JSON-based web service endpoints to demonstrate distributed application functionality.

### Appointment API

Endpoint:

GET /SunriseDentalClinic/api/appointment?number={appointmentNumber}

The endpoint returns appointment information in JSON format for the supplied appointment number.

Example:

GET /SunriseDentalClinic/api/appointment?number=0003

### Dashboard API

Endpoint:

GET /SunriseDentalClinic/api/dashboard

The endpoint returns dashboard summary information in JSON format.

These endpoints demonstrate how application information can be made available to other systems or client applications.

## Database

The application uses a MySQL database named:

sunrise_dental_db

### Main Database Tables

- users
- patients
- dentists
- treatments
- appointments
- bills

Relationships between the tables are maintained using primary keys and foreign keys.

For example:

- An appointment references a patient
- An appointment references a dentist
- An appointment references a treatment
- A bill references an appointment

A unique constraint is used to prevent more than one bill from being created for the same appointment.

## Project Structure

The Java source code is organized into the following packages:

com.sunrise.controller

Contains servlet controllers responsible for handling HTTP requests and responses.

com.sunrise.dao

Contains Data Access Object classes responsible for communicating with the MySQL database using JDBC.

com.sunrise.model

Contains Java model classes representing application data.

com.sunrise.service

Contains service classes responsible for business logic and validation.

com.sunrise.util

Contains utility classes including database connection functionality.

The web application resources are located under:

src/main/webapp

This includes JSP pages, CSS files, and WEB-INF resources.

## Main JSP Pages

The application currently includes:

- login.jsp
- dashboard.jsp
- registerPatient.jsp
- patientList.jsp
- registerAppointment.jsp
- appointmentList.jsp
- appointmentDetails.jsp
- searchAppointment.jsp
- dentistList.jsp
- bill.jsp
- reports.jsp
- help.jsp

## Main Controllers

The application uses servlet controllers for major operations including:

- Login
- Logout
- Dashboard
- Patient registration
- Patient listing
- Dentist listing
- Appointment registration
- Appointment listing
- Appointment searching
- Appointment details
- Appointment status updates
- Billing
- Reports
- Appointment API
- Dashboard API

## Design Patterns

### MVC Pattern

The application separates presentation, request handling, business logic, and data access responsibilities.

JSP pages are mainly responsible for presentation, while servlet controllers process HTTP requests.

### DAO Pattern

Database operations are separated into DAO classes.

This reduces direct database logic inside controllers and helps keep the application organized.

### Service Layer Pattern

Service classes are used for important business operations such as authentication, appointment registration, and billing.

This helps separate business rules from servlet controllers and database operations.

## Validation and Business Rules

The application includes several validation and business rules, including:

- Required login before accessing protected pages
- Required fields for patient registration
- Required fields for appointment registration
- Selection of a valid registered patient
- Prevention of previous dates through the appointment interface
- Dentist double-booking prevention
- Valid appointment status updates
- Prevention of duplicate billing
- Prevention of billing cancelled appointments

Additional validation and testing improvements may be added during continued development.

## Security

The application uses HTTP sessions to maintain authenticated user access.

Protected JSP pages verify that a logged-in user exists in the session before displaying application content.

Logout invalidates the current session.

Further security improvements may be introduced as the project continues to be developed.

## Running the Project

The application is designed to run with the following environment:

- Java
- Eclipse IDE for Enterprise Java and Web Developers
- Apache Tomcat 10.1
- MySQL Server
- MySQL Connector/J

The project is configured as an Eclipse Dynamic Web Project.

### Basic Setup

1. Import or open the project in Eclipse.
2. Configure Apache Tomcat 10.1 in Eclipse.
3. Ensure MySQL Server is running.
4. Create and configure the required Sunrise Dental Clinic database.
5. Ensure MySQL Connector/J is available to the application.
6. Configure the database connection details.
7. Add the project to the Tomcat server.
8. Start Tomcat.
9. Open the application using the configured local Tomcat URL.

During development, the application has been tested locally using Tomcat.

## Version Control

Git is used for source code version control.

The project repository is hosted publicly on GitHub.

Development changes are recorded through Git commits to maintain a history of the project's development.

## Development Status

The following core functionality has been implemented:

- User authentication
- Session management
- Patient registration
- Patient listing and search
- Dentist listing and search
- Appointment registration
- Automatic appointment number generation
- Double-booking prevention
- Appointment listing
- Appointment search
- Appointment filtering
- Appointment status management
- Billing
- Duplicate billing prevention
- Printable receipts
- Dashboard statistics
- Reports
- Printable reports
- Help functionality
- JSON web service endpoints
- Shared user interface styling
- Git version control
- Public GitHub repository

Further work will focus on:

- Automated testing
- Additional validation
- Build automation
- Continuous integration workflow
- Deployment documentation
- Technical documentation
- Assessment report preparation

## Author

Abdul Wahab

CIS6003 Advanced Programming