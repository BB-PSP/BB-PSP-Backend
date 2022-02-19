package bbpsp.backend.domain.player.domain.persist;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "team")
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "team_hometown")
    private String hometown;

    @Column(name = "team_logo_url")
    private String logoUrl;

    @Column(name = "team_head_coach")
    private String headCoach;

    @Column(name = "team_homepage")
    private String homepageUrl;

    @Column(name = "team_founded_at")
    private Long foundedAt;

    @Column(name = "team_colour")
    private String teamColour;

    @Column(name = "team_champ_count")
    private Long champCount;

    @Column(name = "team_last_year")
    private Long lastYear;

    @Column(name = "team_last_place")
    private Long lastPlace;

    @Column(name = "team_last_win")
    private Long lastWin;

    @Column(name = "team_last_draw")
    private Long lastDraw;

    @Column(name = "team_last_defeat")
    private Long lastDefeat;

    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL)
    private List<Year> yearList = new ArrayList<>();

    public static Team createTeam(String name, String hometown, String logoUrl, String headCoach, String homepageUrl,
                                  Long foundedAt, String teamColour, Long champCount, Long lastYear,
                                  Long lastPlace, Long lastWin, Long lastDraw, Long lastDefeat) {
        return new Team(name, hometown, logoUrl, headCoach, homepageUrl,
                foundedAt, teamColour, champCount, lastYear,
                lastPlace, lastWin, lastDraw, lastDefeat);
    }

    public void changeTeam(Team team) {
        this.name = team.getName();
        this.hometown = team.getHometown();
        this.logoUrl = team.getLogoUrl();
        this.headCoach = team.getHeadCoach();
        this.homepageUrl = team.getHomepageUrl();
        this.foundedAt = team.getFoundedAt();
        this.teamColour = team.getTeamColour();
        this.champCount = team.getChampCount();
        this.lastYear = team.getLastYear();
        this.lastPlace = team.getLastPlace();
        this.lastWin = team.getLastWin();
        this.lastDraw = team.getLastDraw();
        this.lastDefeat = team.getLastDefeat();
    }

    private Team(String name, String hometown, String logoUrl, String headCoach, String homepageUrl,
                Long foundedAt, String teamColour, Long champCount, Long lastYear,
                Long lastPlace, Long lastWin, Long lastDraw, Long lastDefeat) {
        this.name = name;
        this.hometown = hometown;
        this.logoUrl = logoUrl;
        this.headCoach = headCoach;
        this.homepageUrl = homepageUrl;
        this.foundedAt = foundedAt;
        this.teamColour = teamColour;
        this.champCount = champCount;
        this.lastYear = lastYear;
        this.lastPlace = lastPlace;
        this.lastWin = lastWin;
        this.lastDraw = lastDraw;
        this.lastDefeat = lastDefeat;
    }
}
