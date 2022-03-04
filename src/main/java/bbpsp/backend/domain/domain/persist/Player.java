package bbpsp.backend.domain.domain.persist;

import bbpsp.backend.domain.enums.BatInfo;
import bbpsp.backend.domain.enums.PitchInfo;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

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
    private int age;

    @Column(name = "player_birth")
    private LocalDate birth;

    @Column(name = "player_position")
    @Enumerated(EnumType.STRING)
    private PositionInfo position;

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

    @Column(name = "player_school")
    private String school;

    @Column(name = "player_salary")
    private int salary;

    @Column(name = "player_fa_remaining")
    private int faRemaining;

    @Column(name = "player_back_number")
    private String backNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "season_id")
//    private Season season;

//    @OneToOne(mappedBy = "player", fetch = LAZY)
//    private BatterStat batterStat;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "batter_stat_id")
    private BatterStat batterStat;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "pitcher_stat_id")
    private PitcherStat pitcherStat;

//    @OneToOne(mappedBy = "player", fetch = LAZY)
//    private BatterStat pitcherStat;

//    @OneToMany(mappedBy = "player")
//    private List<Active> activeList = new ArrayList<>();

    public static Player createPlayer(Team team, String name, String imageUrl, int age, LocalDate birth, PositionInfo position,
                                      BatInfo batInfo, PitchInfo pitchInfo, Double height, Double weight, String school,
                                      int salary, int faRemaining, String backNumber, BatterStat batterStat, PitcherStat pitcherStat) {
        return new Player(team, name, imageUrl, age, birth, position,
                batInfo, pitchInfo, height, weight, school, salary,
                faRemaining, backNumber, batterStat, pitcherStat);
    }

    public void changePlayer(Player player) {
        this.team = player.getTeam();
        this.name = player.getName();
        this.imageUrl = player.getImageUrl();
        this.age = player.getAge();
        this.birth = player.getBirth();
        this.batInfo = player.getBatInfo();
        this.pitchInfo = player.getPitchInfo();
        this.height = player.getHeight();
        this.weight = player.getWeight();
        this.school = player.getSchool();
        this.salary = player.getSalary();
        this.faRemaining = player.getFaRemaining();
        this.backNumber = player.getBackNumber();
        this.batterStat = player.getBatterStat();
        this.pitcherStat = player.getPitcherStat();
    }

    public void changeBatterStat(BatterStat batterStat) {
        this.batterStat = batterStat;
    }

    private Player(Team team, String name, String imageUrl, int age, LocalDate birth, PositionInfo position,
                   BatInfo batInfo, PitchInfo pitchInfo, Double height, Double weight, String school,
                   int salary, int faRemaining, String backNumber, BatterStat batterStat, PitcherStat pitcherStat) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.age = age;
        this.birth = birth;
        this.position = position;
        this.batInfo = batInfo;
        this.pitchInfo = pitchInfo;
        this.height = height;
        this.weight = weight;
        this.school = school;
        this.salary = salary;
        this.faRemaining = faRemaining;
        this.backNumber = backNumber;
        this.team = team;
        this.batterStat = batterStat;
        this.pitcherStat = pitcherStat;
    }

}
