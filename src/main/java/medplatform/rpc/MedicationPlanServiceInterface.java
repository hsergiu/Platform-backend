package medplatform.rpc;

import medplatform.entities.Medication;
import medplatform.entities.MedicationPlan;

import java.util.List;

public interface MedicationPlanServiceInterface {
    List<MedicationPlan> findPlansByPatient(String name);
    MedicationPlan deleteTaken(Medication medication, MedicationPlan medicationPlan);
    void informNotTaken(String name, Medication medication);
}
