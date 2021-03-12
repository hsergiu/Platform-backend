package medplatform.dtos;

import java.util.Objects;
import java.util.UUID;

public class DoctorDTO {


    private UUID id;
    private String username;
    private String password;

    public DoctorDTO() {
    }

    public DoctorDTO(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public DoctorDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return id == doctorDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
