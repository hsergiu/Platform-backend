package medplatform.dtos.builders;

import medplatform.dtos.MedicationPlanDTO;
import medplatform.dtos.MedicationPlanDetailsDTO;
import medplatform.entities.MedicationPlan;

public class MedicationPlanBuilder {

    private MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(), medicationPlan.getIntakeinterval(), medicationPlan.getPeriod(), medicationPlan.getPatient(), medicationPlan.getMedications());
    }

    public static MedicationPlan toEntity(MedicationPlanDetailsDTO medicationPlanDetailsDTO) {
        return new MedicationPlan(medicationPlanDetailsDTO.getIntakeinterval(),
                medicationPlanDetailsDTO.getPeriod(), medicationPlanDetailsDTO.getPatient(), medicationPlanDetailsDTO.getMedications());
    }
}
