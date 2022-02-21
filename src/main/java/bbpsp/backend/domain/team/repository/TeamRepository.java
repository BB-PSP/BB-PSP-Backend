package bbpsp.backend.domain.team.repository;

import bbpsp.backend.domain.team.domain.persist.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t WHERE t.season.id =:seasonId")
    @Transactional(readOnly = true)
    List<Team> findAllByYear(@Param("seasonId")Long seasonId);

    @Query("SELECT t FROM Team t WHERE t.season.id =:seasonId AND t.symbol =:symbolName")
    @Transactional(readOnly = true)
    Optional<Team> findOneByYearAndSymbol(@Param("seasonId")Long yearId, @Param("symbolName")String symbolName);
}
