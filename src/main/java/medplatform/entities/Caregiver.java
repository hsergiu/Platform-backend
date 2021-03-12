package medplatform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
public class Caregiver extends User{

    private static final long serialVersionUID = 1L;

    @Column(name = "birthdate", columnDefinition = "DATE", nullable = false)
    private Date birthdate;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address", nullable = false)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "caregiver")
    private List<Patient> patients;

    public Caregiver() {
        super();
    }

    public Caregiver(String username, String password, Date birthdate, String gender, String address) {
        super(username, password, "caregiver");
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
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

    public List<Patient> getPatients() { return this.patients; }

    public void setPatients(List<Patient> patients) { this.patients = patients; }

}
