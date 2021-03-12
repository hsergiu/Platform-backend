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
public class Medication implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "sideEffects", nullable = false)
    private String sideEffects;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @JsonIgnore
    @ManyToMany(mappedBy = "medications", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<MedicationPlan> medicationplans;

    public Medication() {
    }

    public Medication(String name, String sideEffects, String dosage, List<MedicationPlan> medicationplans) {
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
