package bbpsp.backend.domain.team.domain.persist;

import bbpsp.backend.domain.season.Season;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "team")
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "team_symbol")
    private String symbol;

    @Column(name = "team_hometown")
    private String hometown;

    @Column(name = "team_logo_colour")
    private String colourLogo;

    @Column(name = "team_logo_black")
    private String blackLogo;

    @Column(name = "team_head_coach")
    private String headCoach;

    @Column(name = "team_homepage")
    private String homepageUrl;

    @Column(name = "team_founded_at")
    private int foundedAt;

    @Column(name = "team_colour")
    private String teamColour;

    @Column(name = "team_champ_count")
    private int champCount;

    @Column(name = "team_place")
    private int place;

    @Column(name = "team_win")
    private int winCount;

    @Column(name = "team_draw")
    private int drawCount;

    @Column(name = "team_defeat")
    private int defeatCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

//    @OneToMany(mappedBy = "team")
//    private List<Active> activeList = new ArrayList<>();

    public static Team createTeam(String name, String symbol, String hometown, String logoColour, String blackLogo,
                                  String headCoach, String homepageUrl, int foundedAt, String teamColour, int champCount,
                                  int place, int winCount, int drawCount, int defeatCount, Season season) {
        return new Team(name, symbol, hometown, logoColour, blackLogo, headCoach, homepageUrl,
                foundedAt, teamColour, champCount, place,
                winCount, drawCount, defeatCount, season);
    }

    public void changeTeam(Team team) {
        this.name = team.getName();
        this.symbol = team.getSymbol();
        this.hometown = team.getHometown();
        this.colourLogo = team.getColourLogo();
        this.blackLogo = team.getBlackLogo();
        this.headCoach = team.getHeadCoach();
        this.homepageUrl = team.getHomepageUrl();
        this.foundedAt = team.getFoundedAt();
        this.teamColour = team.getTeamColour();
        this.champCount = team.getChampCount();
        this.place = team.getPlace();
        this.winCount = team.getWinCount();
        this.drawCount = team.getDrawCount();
        this.defeatCount = team.getDefeatCount();
        this.season = team.getSeason();
    }

    private Team(String name, String symbol, String hometown, String colourLogo, String blackLogo,
                 String headCoach, String homepageUrl, int foundedAt, String teamColour, int champCount,
                 int place, int winCount, int drawCount, int defeatCount, Season season) {
        this.name = name;
        this.symbol = symbol;
        this.hometown = hometown;
        this.colourLogo = colourLogo;
        this.blackLogo = blackLogo;
        this.headCoach = headCoach;
        this.homepageUrl = homepageUrl;
        this.foundedAt = foundedAt;
        this.teamColour = teamColour;
        this.champCount = champCount;
        this.place = place;
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.defeatCount = defeatCount;
        this.season = season;
    }
}
