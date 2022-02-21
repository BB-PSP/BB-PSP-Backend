package bbpsp.backend.domain.team.repository;

import bbpsp.backend.domain.player.domain.persist.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    @Query("SELECT s FROM Season s WHERE s.year =:year")
    @Transactional(readOnly = true)
    Optional<Season> findByYear(@Param("year")int year);
}
