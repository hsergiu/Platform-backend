package medplatform.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DoctorDetailsDTO {

    private UUID id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    public DoctorDetailsDTO() {
    }

    public DoctorDetailsDTO(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public DoctorDetailsDTO(String username, String password) {
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

}
