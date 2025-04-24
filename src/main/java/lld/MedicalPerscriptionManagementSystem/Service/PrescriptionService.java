package lld.MedicalPerscriptionManagementSystem.Service;

import lld.AppointmentBookingSystem.inMemoryDb.InMemroyDb;
import lld.MedicalPerscriptionManagementSystem.MedicalPerescriptionDb;
import lld.MedicalPerscriptionManagementSystem.entity.Medicine;
import lld.MedicalPerscriptionManagementSystem.entity.Prescription;
import lld.MedicalPerscriptionManagementSystem.entity.Role;
import lld.MedicalPerscriptionManagementSystem.entity.User;
import lld.MedicalPerscriptionManagementSystem.security.MedicinePrescriptionSecurityContext;

import java.util.*;
import java.util.stream.Collectors;

public class PrescriptionService {
    public String createPrescription(Prescription prescription){
        Map<String, List<Prescription>> patientDoctorToPrescriptionMap = MedicalPerescriptionDb
                .getPatientDoctorToPrescriptionMap();

        List<Prescription> prescriptions = patientDoctorToPrescriptionMap
                .getOrDefault(prescription.getDoctorPatientKey(), new ArrayList<>());
        // check if prescription is unique
        if(!isPerscriptionUnique(prescription.getMedicines(),prescriptions)){
            throw new RuntimeException("prescription must be unique");
        }
        // create entry in
        // patient to prescriptionmap
        addPrescriptionToPatient(prescription);
        prescriptions.add(prescription);
        patientDoctorToPrescriptionMap.put(prescription.getDoctorPatientKey(),prescriptions);
        return "entry created";
    }

    private static void addPrescriptionToPatient(Prescription prescription) {
        List<Prescription> Patientprescriptions = MedicalPerescriptionDb.getPatientPrescriptions()
                .getOrDefault(prescription.getPatientId(), new ArrayList<>());
        Patientprescriptions.add(prescription);
        MedicalPerescriptionDb.getPatientPrescriptions().put(prescription.getPatientId(), Patientprescriptions);
    }

    private boolean isPerscriptionUnique(List<Medicine> medicines, List<Prescription> prescriptions) {
        if (prescriptions.isEmpty()) {
            return true;
        }

        Set<String> newSet = medicines.stream()
                .map(m -> m.getName() + "-" + m.getDosage() + "-" + m.getFrequency())
                .collect(Collectors.toSet());

        for (Prescription existing : prescriptions) {
            Set<String> existingSet = existing.getMedicines().stream()
                    .map(m -> m.getName() + "-" + m.getDosage() + "-" + m.getFrequency())
                    .collect(Collectors.toSet());

            if (existingSet.equals(newSet)) {
                return false; // Duplicate found
            }
        }

        return true;
    }

    public List<Prescription> getPrescriptionByPatientId(Integer patientId){
        User currentUser = MedicinePrescriptionSecurityContext.getCurrentUser();

        // ✅ Only patient themselves or doctors are allowed
        if (currentUser == null) {
            throw new RuntimeException("Not authenticated");
        }

        boolean isPatient = currentUser.getRole() == Role.PATIENT && currentUser.getId().equals(patientId);
        boolean isDoctor = currentUser.getRole() == Role.DOCTOR;

        if (!isPatient && !isDoctor) {
            throw new RuntimeException("Access denied: Unauthorized user");
        }
        List<Prescription> prescriptions = MedicalPerescriptionDb
                .getPatientPrescriptions()
                .getOrDefault(patientId, new ArrayList<>());

       prescriptions.sort(Comparator.comparing(Prescription::getCreatedAt).reversed());
       return prescriptions;
    }
}
