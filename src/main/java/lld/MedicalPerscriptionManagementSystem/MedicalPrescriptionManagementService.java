package lld.MedicalPerscriptionManagementSystem;

import lld.MedicalPerscriptionManagementSystem.Service.PrescriptionService;
import lld.MedicalPerscriptionManagementSystem.entity.*;
import lld.MedicalPerscriptionManagementSystem.security.MedicinePrescriptionSecurityContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MedicalPrescriptionManagementService {
    public static void main(String[] args) {
        // Step 1: Seed users
        User doctor = new User(1, Role.DOCTOR);
        User patient = new User(100, Role.PATIENT);

        // Step 2: Login as doctor
        MedicinePrescriptionSecurityContext.login(doctor);
        PrescriptionService service = new PrescriptionService();

        // Step 3: Create prescription
        Medicine m1 = new Medicine("Paracetamol", Dosage.MG, Frequency.TWICE);
        Medicine m2 = new Medicine("Amoxicillin", Dosage.MG, Frequency.ONCE);

        Prescription prescription = new Prescription();
        prescription.setDoctorId(doctor.getId());
        prescription.setPatientId(patient.getId());
        prescription.setDiagnosisNotes("Fever and throat infection");
        prescription.setCreatedAt(new Date());
        prescription.setMedicines(Arrays.asList(m1, m2));
        prescription.setId(UUID.randomUUID().variant());

        System.out.println(service.createPrescription(prescription));

        // Step 4: Login as patient and view prescriptions
        MedicinePrescriptionSecurityContext.login(patient);
        List<Prescription> history = service.getPrescriptionByPatientId(patient.getId());


    }
}
