package lld.MedicalPerscriptionManagementSystem;

import lld.MedicalPerscriptionManagementSystem.entity.Medicine;
import lld.MedicalPerscriptionManagementSystem.entity.Prescription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalPerescriptionDb {
    private static  final Map<Integer, List<Prescription>> patientToPrescriptionMap = new HashMap<>();
    private static  final Map<String, List<Prescription>>patientDoctorToPrescriptionMap = new HashMap<>();
    private static  final Map<Integer, Medicine>medicineMap = new HashMap<>();

    public MedicalPerescriptionDb() {
    }
    public static Map<Integer, List<Prescription>> getPatientPrescriptions(){
        return patientToPrescriptionMap;
    }

    public static Map<String, List<Prescription>> getPatientDoctorToPrescriptionMap(){
        return patientDoctorToPrescriptionMap;
    }
    public static Map<Integer, Medicine> getMedicineMap(){
        return medicineMap;
    }
}
