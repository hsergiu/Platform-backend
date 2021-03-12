package medplatform.dtos;

import medplatform.entities.Patient;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CaregiverDetailsDTO {

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

    private List<Patient> patients;

    public CaregiverDetailsDTO() {
    }

    public CaregiverDetailsDTO(String username, String password, Date birthdate, String gender, String address) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
    }

    public CaregiverDetailsDTO(UUID id, String username, String password, Date birthdate, String gender, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
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

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public List<Patient> getPatients() { return patients; }

    public void setPatients(List<Patient> patients) { this.patients = patients; }
}
