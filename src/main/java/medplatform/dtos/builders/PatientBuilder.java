package medplatform.dtos.builders;

import medplatform.dtos.PatientDTO;
import medplatform.entities.Patient;
import medplatform.dtos.PatientDetailsDTO;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(), patient.getUsername(), patient.getPassword(), patient.getBirthdate(), patient.getGender(), patient.getAddress(), patient.getMedicalRecord(), patient.getCaregiver());
    }

    public static Patient toEntity(PatientDetailsDTO patientDetailsDTO) {
        return new Patient(patientDetailsDTO.getUsername(), patientDetailsDTO.getPassword(),
                patientDetailsDTO.getBirthdate(), patientDetailsDTO.getGender(),
                patientDetailsDTO.getAddress(), patientDetailsDTO.getMedicalRecord(), patientDetailsDTO.getCaregiver());
    }
}
