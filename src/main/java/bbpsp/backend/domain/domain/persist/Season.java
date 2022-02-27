package bbpsp.backend.domain.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Season {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "season_id")
    private Long id;

    @Column(name = "season_year")
    private int year;


    public static Season createSeason(int year) {
        return new Season(year);
    }

    public void changeSeason(int year) {
        this.year = year;
    }

    private Season(int year) {
        this.year = year;
    }
}
