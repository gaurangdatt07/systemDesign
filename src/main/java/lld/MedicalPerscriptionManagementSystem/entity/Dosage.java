package lld.MedicalPerscriptionManagementSystem.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Dosage {
    MG("mg"),_1_TBSP("tablespoon");
    private String dosage;

    Dosage(String value) {
        this.dosage = value;
    }

    public String getValue() {
        return dosage;
    }

    public static Optional<Dosage> fromValue(String input) {
        return Arrays.stream(Dosage.values())
                .filter(d -> d.dosage.equalsIgnoreCase(input))
                .findFirst();
    }
}
