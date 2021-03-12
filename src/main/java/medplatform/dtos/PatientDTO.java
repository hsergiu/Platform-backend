package medplatform.dtos;

import medplatform.entities.Caregiver;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientDTO {

    private UUID id;
    private String username;
    private String password;
    private Date birthdate;
    private String gender;
    private String address;
    private String medicalRecord;
    private Caregiver caregiver;

    public PatientDTO() {
    }

    public PatientDTO(UUID id, String username, String password, Date birthdate, String gender, String address, String medicalRecord, Caregiver caregiver) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.caregiver = caregiver;
    }

     public PatientDTO(String username, String password, Date birthdate, String gender, String address, String medicalRecord, Caregiver caregiver) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO patientDTO = (PatientDTO) o;
        return id == patientDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
