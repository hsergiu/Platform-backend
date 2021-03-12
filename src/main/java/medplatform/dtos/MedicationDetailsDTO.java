package medplatform.dtos;

import medplatform.entities.MedicationPlan;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class MedicationDetailsDTO {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String sideEffects;
    @NotNull
    private String dosage;
    private List<MedicationPlan> medicationplans;

    public MedicationDetailsDTO() {
    }

    public MedicationDetailsDTO(UUID id, String name, String sideEffects, String dosage, List<MedicationPlan> medicationplans) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.medicationplans = medicationplans;
    }

    public MedicationDetailsDTO(String name, String sideEffects, String dosage, List<MedicationPlan> medicationplans) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.medicationplans = medicationplans;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSideEffects() { return sideEffects; }

    public void setSideEffects(String sideEffects) { this.sideEffects = sideEffects; }

    public String getDosage() { return dosage; }

    public void setDosage(String dosage) { this.dosage = dosage; }

    public List<MedicationPlan> getMedicationPlans() { return medicationplans; }

    public void setMedicationPlans(List<MedicationPlan> medicationplans) { this.medicationplans = medicationplans; }
}
