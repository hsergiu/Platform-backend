package medplatform.repositories;

import medplatform.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID>{
}
