package medplatform.dtos;

import medplatform.entities.Caregiver;
import medplatform.entities.MedicationPlan;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PatientDetailsDTO {

    private UUID id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Date birthdate;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    @NotNull
    private String medicalRecord;
    private Caregiver caregiver;
    private List<MedicationPlan> medicationPlans;

    public PatientDetailsDTO() {
    }

    public PatientDetailsDTO(UUID id, String username, String password, Date birthdate, String gender, String address, String medicalRecord, Caregiver caregiver) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.caregiver = caregiver;
    }

    public PatientDetailsDTO(String username, String password, Date birthdate, String gender, String address, String medicalRecord, Caregiver caregiver) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.caregiver = caregiver;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalRecord() { return medicalRecord; }

    public void setMedicalRecord(String medicalRecord) { this.medicalRecord = medicalRecord; }

    public Caregiver getCaregiver() { return caregiver; }

    public void setCaregiver(Caregiver caregiver) { this.caregiver = caregiver; }

    public List<MedicationPlan> getMedicationPlans() { return medicationPlans; }

    public void setMedicationPlans(List<MedicationPlan> medicationPlans) { this.medicationPlans = medicationPlans; }
}
