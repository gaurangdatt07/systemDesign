package lld.AppointmentBookingSystem.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Appointment {
    private Integer id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Status status;
    private Doctor doctor;
    private Patient patient;


    public Integer getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Status getStatus() {
        return status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Appointment(Integer id, LocalDateTime startTime, LocalDateTime endTime, Status status, Doctor doctor, Patient patient) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.doctor = doctor;
        this.patient = patient;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
