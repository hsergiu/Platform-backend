package medplatform.dtos;

import medplatform.entities.Medication;
import medplatform.entities.Patient;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class MedicationPlanDetailsDTO {

    private UUID id;
    @NotNull
    private String intakeinterval;
    @NotNull
    private String period;
    private Patient patient;
    private List<Medication> medications;

    public MedicationPlanDetailsDTO() {
    }

    public MedicationPlanDetailsDTO(UUID id, String intakeinterval, String period, Patient patient, List<Medication> medications) {
        this.id = id;
        this.intakeinterval = intakeinterval;
        this.period = period;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlanDetailsDTO(String intakeinterval, String period, Patient patient, List<Medication> medications) {
        this.intakeinterval = intakeinterval;
        this.period = period;
        this.patient = patient;
        this.medications = medications;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIntakeinterval() {
        return intakeinterval;
    }

    public void setIntakeinterval(String intakeinterval) {
        this.intakeinterval = intakeinterval;
    }

    public String getPeriod() { return period; }

    public void setPeriod(String period) { this.period = period; }

    public Patient getPatient() { return patient; }

    public void setPatient(Patient patient) { this.patient = patient; }

    public List<Medication> getMedications() { return medications; }

    public void setMedications(List<Medication> medications) { this.medications = medications; }
}
