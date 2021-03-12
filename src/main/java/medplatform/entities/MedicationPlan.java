package medplatform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class MedicationPlan implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "intakeinterval", nullable = false)
    private String intakeinterval;

    @Column(name = "period", nullable = false)
    private String period;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idpatient", nullable = false)
    private Patient patient;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "meds_plan",
            joinColumns = @JoinColumn(name = "id_plan"),
            inverseJoinColumns = @JoinColumn(name = "id_med"))
    private List<Medication> medications;

    public MedicationPlan() {
    }

    public MedicationPlan(String intakeinterval,  String period, Patient patient, List<Medication> medication) {
        this.intakeinterval = intakeinterval;
        this.period = period;
        this.patient = patient;
        this.medications = medication;
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
