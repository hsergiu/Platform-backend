package medplatform.repositories;

import medplatform.entities.MonitoredData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonitoredDataRepository extends JpaRepository<MonitoredData, UUID> {
}
