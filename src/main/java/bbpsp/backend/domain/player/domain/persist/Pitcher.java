package bbpsp.backend.domain.player.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter
@Table(name = "pitcher")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pitcher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pitcher_id")
    private Long id;

    @Column(name = "pitcher_position")
    private String position;

    @Column(name = "pitcher_age")
    private Long age;

    @Column(name = "pitcher_back_number")
    private Long backNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    private Year year;

    @OneToOne(mappedBy = "pitcher", fetch = LAZY)
    PitcherStat pitcherStat;

    public static Pitcher createPitcher(String position, Long age, Long backNumber, Year year) {
        return new Pitcher(position, age, backNumber, year);
    }

    public void changePitcher(Pitcher pitcher) {
        this.position = pitcher.getPosition();
        this.age = pitcher.getAge();
        this.backNumber = pitcher.getBackNumber();
        this.year = pitcher.getYear();
    }

    private Pitcher(String position, Long age, Long backNumber, Year year) {
        this.position = position;
        this.age = age;
        this.backNumber = backNumber;
        this.year = year;
    }
}
