package lld.AppointmentBookingSystem;

import lld.AppointmentBookingSystem.Service.AppointmentService;
import lld.AppointmentBookingSystem.entities.Appointment;
import lld.AppointmentBookingSystem.entities.Doctor;
import lld.AppointmentBookingSystem.entities.Patient;
import lld.AppointmentBookingSystem.entities.Status;
import lld.AppointmentBookingSystem.inMemoryDb.InMemroyDb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AppointmentBookingSystem {
    public static void main(String[] args) {
        // Seed in-memory database
        Doctor doctor = new Doctor(1, "Dr. Gaurang");
        Patient patient1 = new Patient(100, "John Doe");
        Patient patient2 = new Patient(101, "Jane Doe");

        InMemroyDb.getDoctorMap().put(doctor.getId(), doctor);
        InMemroyDb.getPatientMap().put(patient1.getId(), patient1);
        InMemroyDb.getPatientMap().put(patient2.getId(), patient2);
        InMemroyDb.getDoctorSlots().put(doctor.getId(), new ArrayList<>());

        // Pre-booked appointment: 10:00 - 10:30
        LocalDateTime preBookedStart = LocalDateTime.of(2025, Month.APRIL, 26, 10, 0);
        LocalDateTime preBookedEnd = preBookedStart.plusMinutes(30);
        Appointment preBooked = new Appointment(1, preBookedStart, preBookedEnd, Status.SCHEDULED, doctor, patient1);
        InMemroyDb.getDoctorSlots().get(doctor.getId()).add(preBooked);

        AppointmentService service = new AppointmentService();

        System.out.println("----- Available Slots Before Booking -----");
        List<String> slotsBefore = service.getAvailableAppointments(doctor.getId(), LocalDate.of(2025, 4, 26));
        slotsBefore.forEach(System.out::println);

        System.out.println("\n----- Booking New Appointment (11:00 - 11:30) -----");
        try {
            String result = service.bookAppointment(patient2.getId(), doctor.getId(),
                    LocalDateTime.of(2025, 4, 26, 11, 0));
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n----- Trying Overlapping Booking (10:15 - 10:45) -----");
        try {
            service.bookAppointment(patient2.getId(), doctor.getId(),
                    LocalDateTime.of(2025, 4, 26, 10, 15));
        } catch (RuntimeException e) {
            System.out.println("Overlap prevented: " + e.getMessage());
        }

        System.out.println("\n----- Cancel Appointment ID 1 -----");
        try {
            String cancelResult = service.cancelAppointment(1);
            System.out.println(cancelResult);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n----- Available Slots After Cancellation -----");
        List<String> slotsAfter = service.getAvailableAppointments(doctor.getId(), LocalDate.of(2025, 4, 26));
        slotsAfter.forEach(System.out::println);
    }
}
