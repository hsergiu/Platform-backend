package medplatform.repositories;

import medplatform.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID>{
    Optional<Medication> findByName(String name);
}
