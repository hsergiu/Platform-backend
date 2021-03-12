package medplatform.repositories;

import medplatform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;


@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, UUID> {
    Optional<T> findByUsername(String username);

    Boolean existsByUsername(String username);

}
