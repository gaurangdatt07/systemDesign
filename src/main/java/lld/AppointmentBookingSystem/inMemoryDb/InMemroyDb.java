package lld.AppointmentBookingSystem.inMemoryDb;

import lld.AppointmentBookingSystem.entities.Appointment;
import lld.AppointmentBookingSystem.entities.Doctor;
import lld.AppointmentBookingSystem.entities.Patient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemroyDb {

    private static final Map<Integer, Doctor> doctorMap = new ConcurrentHashMap<>();
    private static final Map<Integer, Patient>patientMap=new ConcurrentHashMap<>();;
    private static final Map<Integer, List<Appointment>>doctorSlots=new ConcurrentHashMap<>();;


    public InMemroyDb() {
    }

    public static Map<Integer, Doctor> getDoctorMap() {
        return doctorMap;
    }

    public static Map<Integer, Patient> getPatientMap() {
        return patientMap;
    }

    public static Map<Integer, List<Appointment>> getDoctorSlots() {
        return doctorSlots;
    }
}
