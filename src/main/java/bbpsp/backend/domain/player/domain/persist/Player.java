package bbpsp.backend.domain.player.domain.persist;

import bbpsp.backend.domain.player.enums.BatInfo;
import bbpsp.backend.domain.player.enums.PitchInfo;
import bbpsp.backend.domain.season.Season;
import bbpsp.backend.domain.team.domain.persist.Team;
import bbpsp.backend.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity @Getter
@Table(name = "player")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Player extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_name")
    private String name;

    @Column(name = "player_image_url")
    private String imageUrl;

    @Column(name = "player_age")
    private Long age;

    @Column(name = "player_birth")
    private LocalDate birth;

    @Column(name = "bat_info")
    @Enumerated(EnumType.STRING)
    private BatInfo batInfo; // 우타, 좌타, 스위치

    @Column(name = "pitch_info")
    @Enumerated(EnumType.STRING)
    private PitchInfo pitchInfo; // 우완오버, 우완언더, 좌완사이드, ...

    @Column(name = "player_height")
    private Double height;

    @Column(name = "player_weight")
    private Double weight;

    @Column(name = "player_high_school")
    private String highSchool;

    @Column(name = "player_univ")
    private String university;

    @Column(name = "player_salary")
    private Long salary;

    @Column(name = "player_fa_remaining")
    private Long faRemaining;

    @Column(name = "player_back_number")
    private Long backNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

//    @OneToMany(mappedBy = "player")
//    private List<Active> activeList = new ArrayList<>();

    public static Player createPlayer(Team team, Season season, String name, String imageUrl, Long age, LocalDate birth,
                                      BatInfo batInfo, PitchInfo pitchInfo, Double height, Double weight,
                                      String highSchool, String university, Long salary, Long faRemaining, Long backNumber) {
        return new Player(team, season, name, imageUrl, age, birth, batInfo, pitchInfo, height, weight, highSchool, university, salary, faRemaining, backNumber);
    }

    private Player(Team team, Season season, String name, String imageUrl, Long age, LocalDate birth, BatInfo batInfo,
                   PitchInfo pitchInfo, Double height, Double weight, String highSchool,
                   String university, Long salary, Long faRemaining, Long backNumber) {
        this.team = team;
        this.season = season;
        this.name = name;
        this.imageUrl = imageUrl;
        this.age = age;
        this.birth = birth;
        this.batInfo = batInfo;
        this.pitchInfo = pitchInfo;
        this.height = height;
        this.weight = weight;
        this.highSchool = highSchool;
        this.university = university;
        this.salary = salary;
        this.faRemaining = faRemaining;
        this.backNumber = backNumber;
    }

    public void changePlayer(Player player) {
        this.team = player.getTeam();
        this.season = player.getTeam().getSeason();
        this.name = player.getName();
        this.imageUrl = player.getImageUrl();
        this.age = player.getAge();
        this.birth = player.getBirth();
        this.batInfo = player.getBatInfo();
        this.pitchInfo = player.getPitchInfo();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.highSchool = player.getHighSchool();
        this.university = player.getUniversity();
        this.salary = player.getSalary();
        this.faRemaining = player.getFaRemaining();
        this.backNumber = player.getBackNumber();
    }
}
