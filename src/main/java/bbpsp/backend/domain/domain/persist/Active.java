package bbpsp.backend.domain.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter
@Table(name = "active")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Active {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "active_id")
    private Long id;

    @Column(name = "year")
    private Long year;

    @Column(name = "is_first_season")
    private Boolean isFirstSeason;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

//    @OneToMany(mappedBy = "active")
//    private List<Batter> batters = new ArrayList<>();
//
//    @OneToMany(mappedBy = "active")
//    private List<Pitcher> pitchers = new ArrayList<>();

    public static Active createActive(Player player, Team team, Long year, Boolean isFirstSeason){
        return new Active(player, team, year, isFirstSeason);
    }

    private Active(Player player, Team team, Long year, Boolean isFirstSeason) {
        this.player = player;
        this.team = team;
        this.year = year;
        this.isFirstSeason = isFirstSeason;
    }

    public void changeActive(Long year, Boolean isFirstSeason) {
        this.year = year;
        this.isFirstSeason = isFirstSeason;
    }
}
