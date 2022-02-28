package bbpsp.backend.domain.repository;

import bbpsp.backend.domain.domain.persist.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t WHERE t.season.id =:seasonId ORDER BY t.place ASC")
    @Transactional(readOnly = true)
    List<Team> findAllByYear(@Param("seasonId")Long seasonId);

    @Query("SELECT t FROM Team t WHERE t.season.id =:seasonId AND t.name LIKE %:symbolName%")
    @Transactional(readOnly = true)
    Optional<Team> findOneByYearAndSymbol(@Param("seasonId")Long yearId, @Param("symbolName")String symbolName);
}
