package bbpsp.backend.domain.repository;


import bbpsp.backend.domain.domain.persist.BatterStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatterStatRepository extends JpaRepository<BatterStat, Long> {

//    @Query("select b from BatterStat b where b.player.id = :playerId")
//    Optional<BatterStat> findOneByPlayerId(@Param("playerId") Long playerId);
}
