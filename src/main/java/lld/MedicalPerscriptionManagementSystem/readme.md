# 🩺 Medical Prescription Management System – LLD Specification

This system enables doctors to securely upload medical prescriptions for patients, and allows patients to view/download them post consultation.

---

## 🎯 Functional Requirements

### 👨‍⚕️ Doctor Actions
- Upload a **prescription** after an appointment
- Each prescription includes:
    - Doctor ID, Patient ID
    - List of prescribed **medicines** (name, dosage, frequency)
    - **Diagnosis notes**
    - Timestamp (auto-generated)
- Cannot modify a prescription once submitted

### 👤 Patient Actions
- View/download their **own prescriptions**
- See prescription history in **reverse chronological** order

---

## 🔐 Access Control

| Role     | Can Upload | Can View | Can Edit |
|----------|------------|----------|----------|
| Doctor   | ✅ (for their own patient) | ✅ (only own prescriptions) | ❌ |
| Patient  | ❌         | ✅ (only own prescriptions) | ❌ |
| Others   | ❌         | ❌        | ❌ |

---

## 🧱 Data Model (Conceptual Entities)

### Prescription
- `id`: UUID
- `doctor`: reference to Doctor
- `patient`: reference to Patient
- `diagnosisNotes`: string
- `timestamp`: creation time
- `List<Medicine> medicines`

### Medicine
- `name`: e.g., Paracetamol
- `dosage`: e.g., 500mg
- `frequency`: e.g., Twice a day

---

## 📦 Non-Functional Goals
- 📁 Immutable prescriptions (read-only after creation)
- 🔒 Role-based access to sensitive records
- 📄 Readable format for patient download (plain or PDF)
- 📊 Optional: Paginated history for patients

---

## 🧠 Bonus Features (Optional)
- Attach prescriptions directly to an **appointment**
- Export prescriptions in **PDF or printable** format
- Audit log for access (who viewed and when)

---

## 💻 Tech Stack Suggestion (In-Memory LLD)
- Java 17+
- Spring Boot (optional)
- Map-based storage for Doctors, Patients, Prescriptions
- DTO classes for secure prescription views
