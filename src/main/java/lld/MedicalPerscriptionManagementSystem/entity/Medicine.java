package lld.MedicalPerscriptionManagementSystem.entity;

public class Medicine {
    private Integer id;
    private String name;
    private Dosage dosage;
    private  Frequency frequency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Medicine(String name, Dosage dosage, Frequency frequency) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
    }
}
