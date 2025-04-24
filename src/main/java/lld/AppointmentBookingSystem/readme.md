# 🩺 Appointment Booking System - Low Level Design

This document outlines the low-level design (LLD) for an Appointment Booking System, typically used in healthcare platforms like Practo.

---

## 📌 Functional Requirements

- Patients can book appointments with doctors
- Doctors can view their scheduled appointments
- Patients can cancel or reschedule existing appointments
- A doctor cannot be double-booked for overlapping time slots
- A patient cannot book two appointments at the same time
- Optional: Appointment reminders via notifications

---

## ⚙️ Entities

### 1. Doctor
Stores doctor-specific information.

### 2. Patient
Stores patient-specific information.

### 3. Appointment
Represents a scheduled time between a doctor and a patient.

---

## 🔁 Relationships

- One doctor ➝ many appointments
- One patient ➝ many appointments
- One appointment ➝ one doctor and one patient

---

## 🛡️ Constraints

- Prevent overlapping appointments for doctors and patients
- Appointments have a `startTime`, `endTime`, and `status`:
    - `SCHEDULED`
    - `CANCELLED`
    - `COMPLETED`

---

## 🔒 Access Control

- Only the doctor or patient involved in an appointment can view it
- Admins may have privileged access
- Role-based access handled via Spring Security

---

## 📥 APIs (Example Endpoints)

| Method | Endpoint                    | Description                        |
|--------|-----------------------------|------------------------------------|
| POST   | `/appointments`             | Book a new appointment             |
| GET    | `/appointments/{id}`        | View an appointment                |
| GET    | `/appointments?doctorId=x`  | View all appointments for a doctor |
| POST   | `/appointments/cancel/{id}`| Cancel an appointment              |

---

## 🧠 Bonus Enhancements (Optional)

- Predefined doctor slot availability
- Feedback/rating system post-appointment
- Notification system using Redis or async jobs
- Pagination and filters for appointment history

---

## 🧪 Technologies

- Java, Spring Boot
- MySQL/PostgreSQL
- Redis (optional for reminders)
- Spring Security for RBAC
