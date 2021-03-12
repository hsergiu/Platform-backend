package medplatform.dtos.builders;

import medplatform.dtos.CaregiverDTO;
import medplatform.dtos.CaregiverDetailsDTO;
import medplatform.entities.Caregiver;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(), caregiver.getUsername(), caregiver.getPassword(),
                caregiver.getBirthdate(), caregiver.getGender(), caregiver.getAddress());
    }

    public static Caregiver toEntity(CaregiverDetailsDTO caregiverDetailsDTO) {
        return new Caregiver(caregiverDetailsDTO.getUsername(), caregiverDetailsDTO.getPassword(),
                caregiverDetailsDTO.getBirthdate(), caregiverDetailsDTO.getGender(),
                caregiverDetailsDTO.getAddress());
    }
}
