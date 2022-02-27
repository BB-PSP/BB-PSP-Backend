package bbpsp.backend.domain.repository;

import bbpsp.backend.domain.domain.persist.PlayerUnique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerUniqueRepository extends JpaRepository<PlayerUnique, Long> {
}
