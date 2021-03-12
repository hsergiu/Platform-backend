package medplatform.dtos;

import medplatform.entities.Medication;
import medplatform.entities.Patient;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicationPlanDTO {


    private UUID id;
    private String intakeinterval;
    private String period;
    private Patient patient;
    private List<Medication> medications;

    public MedicationPlanDTO() {
    }

    public MedicationPlanDTO(UUID id, String intakeinterval,  String period, Patient patient, List<Medication> medications) {
        this.id = id;
        this.intakeinterval = intakeinterval;
        this.period = period;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlanDTO(String intakeinterval,  String period, Patient patient, List<Medication> medications) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanDTO medicationPlanDTO = (MedicationPlanDTO) o;
        return id == medicationPlanDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
