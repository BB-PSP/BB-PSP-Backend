package bbpsp.backend.domain.repository;

import bbpsp.backend.domain.domain.persist.PitcherStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherStatRepository extends JpaRepository<PitcherStat, Long> {
}
