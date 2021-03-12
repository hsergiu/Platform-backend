package medplatform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Patient extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "birthdate", columnDefinition = "DATE", nullable = false)
    private Date birthdate;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "medicalRecord", nullable = false)
    private String medicalRecord;

    @ManyToOne
    @JoinColumn(name = "idcaregiver")
    private Caregiver caregiver;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<MedicationPlan> medicationPlans;

    public Patient() {
        super();
    }

    public Patient(String username, String password, Date birthdate, String gender, String address, String medicalRecord, Caregiver caregiver) {
        super(username, password, "patient");
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.caregiver = caregiver;
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

    public String getMedicalRecord(){ return medicalRecord; }

    public void setMedicalRecord(String medicalRecord) { this.medicalRecord = medicalRecord; }

    public Caregiver getCaregiver() { return caregiver; }

    public void setCaregiver(Caregiver caregiver) { this.caregiver = caregiver; }

    public List<MedicationPlan> getMedicationPlans() { return medicationPlans; }

    public void setMedicationPlans(List<MedicationPlan> medicationPlans) { this.medicationPlans = medicationPlans; }

}
