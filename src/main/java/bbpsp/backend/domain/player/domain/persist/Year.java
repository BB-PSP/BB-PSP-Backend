package bbpsp.backend.domain.player.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Year {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id")
    private Long id;

    @Column(name = "year")
    private Long year;

    @Column(name = "is_first_season")
    private Boolean isFirstSeason;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public static Year createYear(Player player, Team team, Long year, Boolean isFirstSeason){
        return new Year(player, team, year, isFirstSeason);
    }

    private Year(Player player, Team team, Long year, Boolean isFirstSeason) {
        this.player = player;
        this.team = team;
        this.year = year;
        this.isFirstSeason = isFirstSeason;
    }

    public void changeYearEntity(Long year, Boolean isFirstSeason) {
        this.year = year;
        this.isFirstSeason = isFirstSeason;
    }
}
