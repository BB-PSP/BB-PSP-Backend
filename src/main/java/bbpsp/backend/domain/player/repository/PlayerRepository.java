package bbpsp.backend.domain.player.repository;

import bbpsp.backend.domain.player.domain.persist.Active;
import bbpsp.backend.domain.player.domain.persist.Player;
import bbpsp.backend.domain.team.domain.persist.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p" +
            " LEFT JOIN FETCH p.team t" +
            " LEFT JOIN FETCH p.season s" +
            " WHERE p.name =:name")
    @Transactional(readOnly = true)
    List<Player> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM Player p LEFT JOIN FETCH p.season s WHERE p.season.id =:seasonId")
    @Transactional(readOnly = true)
    List<Player> findAllByYearId(@Param("seasonId")Long seasonId);
}
