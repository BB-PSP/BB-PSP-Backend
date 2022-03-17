package bbpsp.backend.domain.dto.response;

import bbpsp.backend.domain.domain.persist.Team;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class TeamDTO {

    @ApiModelProperty(value = "팀 명(영문)", required = true, example = "Hanwha Eagles")
    private final String name;

    @ApiModelProperty(value = "연고지", required = true, example = "대전")
    private final String hometown;

    @ApiModelProperty(value = "컬러 로고 url", required = true, example = "serverURL/logoName_colour.png")
    private final String colourLogo;

    @ApiModelProperty(value = "흑백 로고 url", required = true, example = "serverURL/logoName_black.png")
    private final String blackLogo;

    @ApiModelProperty(value = "감독", required = true, example = "수베로")
    private final String headCoach;

    @ApiModelProperty(value = "팀 홈페이지 주소", required = true, example = "https://www.hanwhaeagles.co.kr/index.do")
    private final String homepageUrl;

    @ApiModelProperty(value = "설립 년도", required = true, example = "1983")
    private final int foundedAt;

    @ApiModelProperty(value = "팀 컬러", required = true, example = "#ff6600")
    private final String teamColour;

    @ApiModelProperty(value = "우승 횟수", required = true, example = "1")
    private final int champCount;

    @ApiModelProperty(value = "해당 시즌 승 수(2021이 기본)", required = true, example = "49")
    private final int winCount;

    @ApiModelProperty(value = "해당 시즌 무승부 수(2021이 기본)", required = true, example = "12")
    private final int drawCount;

    @ApiModelProperty(value = "해당 시즌 패배 수(2021이 기본)", required = true, example = "83")
    private final int defeatCount;

    @ApiModelProperty(value = "해당 시즌 최종 순위(포스트 시즌 포함)", required = true, example = "3")
    private int place;

    @ApiModelProperty(value = "linearGradient", required = true, example = "180deg, #000000 3.12%, rgba(0, 0, 0, 0.9) 53.65% ...")
    private String linearGradient;

    public static TeamDTO createTeamDTO(Team team) {
        return new TeamDTO(team.getName(), team.getHometown(), team.getColourLogo(), team.getBlackLogo(),
                team.getHeadCoach(), team.getHomepageUrl(), team.getFoundedAt(),
                team.getTeamColour(), team.getChampCount(), team.getWinCount(),
                team.getDrawCount(), team.getDefeatCount(), team.getPlace(), team.getLinearGradient());
    }

    private TeamDTO(String name, String hometown, String colourLogo, String blackLogo,
                   String headCoach, String homepageUrl, int foundedAt, String teamColour,
                   int champCount, int winCount, int drawCount, int defeatCount, int place, String linearGradient) {
        this.name = name;
        this.hometown = hometown;
        this.colourLogo = colourLogo;
        this.blackLogo = blackLogo;
        this.headCoach = headCoach;
        this.homepageUrl = homepageUrl;
        this.foundedAt = foundedAt;
        this.teamColour = teamColour;
        this.champCount = champCount;
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.defeatCount = defeatCount;
        this.linearGradient = linearGradient;
        this.place = place;
    }
}
