package bbpsp.backend.domain.player.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter
@Table(name = "batter")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Batter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batter_id")
    private Long id;

    @Column(name = "batter_position")
    private String position;

    @Column(name = "batter_age")
    private Long age;

    @Column(name = "batter_back_number")
    private Long backNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_id")
    private Year year;

    @OneToOne(mappedBy = "batter", fetch = LAZY)
    BatterStat batterStat;

    public static Batter createBatter(String position, Long age, Long backNumber, Year year) {
        return new Batter(position, age, backNumber, year);
    }

    public void changeBatter(Batter batter) {
        this.position = batter.getPosition();
        this.age = batter.getAge();
        this.backNumber = batter.getBackNumber();
        this.year = batter.getYear();
    }

    private Batter(String position, Long age, Long backNumber, Year year) {
        this.position = position;
        this.age = age;
        this.backNumber = backNumber;
        this.year = year;
    }
}
