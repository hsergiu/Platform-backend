package medplatform.dtos;

import medplatform.entities.Patient;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CaregiverDTO {

    private UUID id;
    private String username;
    private String password;
    private Date birthdate;
    private String gender;
    private String address;

    private List<Patient> patients;

    public CaregiverDTO() {
    }

    public CaregiverDTO(String username, String password, Date birthdate, String gender, String address) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
    }

    public CaregiverDTO(UUID id, String username, String password, Date birthdate, String gender, String address) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDTO caregiverDTO = (CaregiverDTO) o;
        return birthdate == caregiverDTO.birthdate &&
                Objects.equals(username, caregiverDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, birthdate);
    }
}
