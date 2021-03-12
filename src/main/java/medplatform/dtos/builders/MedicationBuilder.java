package medplatform.dtos.builders;

import medplatform.dtos.MedicationDTO;
import medplatform.dtos.MedicationDetailsDTO;
import medplatform.entities.Medication;

public class MedicationBuilder {

    private MedicationBuilder() {
    }

    public static MedicationDTO toMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(), medication.getName(), medication.getSideEffects(), medication.getDosage());
    }

    public static Medication toEntity(MedicationDetailsDTO medicationDetailsDTO) {
        return new Medication(medicationDetailsDTO.getName(),
                medicationDetailsDTO.getSideEffects(),
                medicationDetailsDTO.getDosage(), medicationDetailsDTO.getMedicationPlans());
    }
}
