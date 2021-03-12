package medplatform.entities;

import javax.persistence.Entity;

@Entity
public class Doctor extends User {

    private static final long serialVersionUID = 1L;

    public Doctor() {
        super();
    }

    public Doctor(String username, String password) {
        super(username, password, "doctor");
    }

}
