package bbpsp.backend.domain.repository;

import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.enums.PositionInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p" +
            " LEFT JOIN FETCH p.team t" +
            " LEFT JOIN FETCH t.season s" +
            " WHERE p.name =:name")
    @Transactional(readOnly = true)
    List<Player> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM Player p " +
            " LEFT JOIN FETCH p.team t" +
            " LEFT JOIN FETCH t.season s WHERE s.id =:seasonId")
    @Transactional(readOnly = true)
    List<Player> findAllByYear(@Param("seasonId")Long seasonId);

    @Query("SELECT p FROM Player p" +
            " LEFT JOIN FETCH p.batterStat b" +
            " LEFT JOIN FETCH p.team t" +
            " LEFT JOIN FETCH t.season s" +
            " WHERE s.id =:seasonId")
    Slice<Player> findPlayerByYear(@Param("seasonId")Long seasonId, Pageable pageable);

    @Query("SELECT p FROM Player p" +
            " LEFT JOIN FETCH p.batterStat b" +
            " LEFT JOIN FETCH p.team t" +
            " LEFT JOIN FETCH t.season s" +
            " WHERE p.name =:name AND p.birth =:birth")
    List<Player> findByNameAndBirth(@Param("name") String name, @Param("birth") LocalDate birth);

    @Query("SELECT p FROM Player p" +
            " LEFT JOIN FETCH p.team t" +
            " WHERE p.name =:name AND p.birth =:birth")
    List<Player> findAllByNameAndBirth(@Param("name")String name, @Param("birth")LocalDate birth);

    @Query("SELECT p FROM Player p" +
            " LEFT JOIN FETCH p.team t" +
            " LEFT JOIN FETCH t.season s" +
            " WHERE t.id =:teamId")
    List<Player> findAllByTeamId(@Param("teamId")Long teamId);
}
