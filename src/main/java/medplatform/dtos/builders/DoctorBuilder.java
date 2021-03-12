package medplatform.dtos.builders;

import medplatform.dtos.DoctorDTO;
import medplatform.dtos.DoctorDetailsDTO;
import medplatform.entities.Doctor;

public class DoctorBuilder {

    private DoctorBuilder() {
    }

    public static DoctorDTO toDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId(), doctor.getUsername(), doctor.getPassword());
    }

    public static Doctor toEntity(DoctorDetailsDTO doctorDetailsDTO) {
        return new Doctor(doctorDetailsDTO.getUsername(), doctorDetailsDTO.getPassword());
    }
}
