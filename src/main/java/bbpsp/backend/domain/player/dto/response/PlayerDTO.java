package bbpsp.backend.domain.player.dto.response;

import bbpsp.backend.domain.player.domain.persist.Player;
import bbpsp.backend.domain.player.enums.BatInfo;
import bbpsp.backend.domain.player.enums.PitchInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
public class PlayerDTO {

    @ApiModelProperty(value = "시즌", required = true, example = "2021")
    private int year;

    @ApiModelProperty(value = "이름", required = true, example = "김한화")
    private String name;

    @ApiModelProperty(value = "팀", required = true, example = "한화 이글스")
    private String team;

    @ApiModelProperty(value = "프로필 이미지 url", required = true, example = "서버url/image.png")
    private String imageUrl;

    @ApiModelProperty(value = "나이", required = true, example = "21")
    private Long age;

    @ApiModelProperty(value = "출생", required = true, example = "1993-01-01")
    private LocalDate birth;

    @ApiModelProperty(value = "타격 손 정보(우타, 좌타, 스위치) 이니셜로 표시함", required = true, example = "RIGHT, LEFT, SWITCH")
    @Enumerated(EnumType.STRING)
    private BatInfo batInfo; // 우타, 좌타, 스위치

    @ApiModelProperty(value = "투구 손 정보(우완오버, 우완언더, ...) 이니셜로 표시함", required = true, example = "RIGHT_OVER, LEFT_UNDER")
    @Enumerated(EnumType.STRING)
    private PitchInfo pitchInfo; // 우완오버, 우완언더, 좌완사이드, ...

    @ApiModelProperty(value = "키(cm)", required = true, example = "180")
    private Double height;

    @ApiModelProperty(value = "몸무게(kg)", required = true, example = "75")
    private Double weight;

    @ApiModelProperty(value = "출신 고교", required = true, example = "중앙고")
    private String highSchool;

    @ApiModelProperty(value = "출신 대학(null 가능)", required = false, example = "건국대")
    private String university;

    @ApiModelProperty(value = "연봉(만원 단위)", required = true, example = "12000(=1억2천)")
    private Long salary;

    @ApiModelProperty(value = "FA 잔여 기간(연 단위)", required = true, example = "3")
    private Long faRemaining;

    @ApiModelProperty(value = "등번호", required = true, example = "99")
    private Long backNumber;

    public static PlayerDTO createPlayerDTO(Player player) {
        return new PlayerDTO(player.getSeason().getYear(), player.getName(), player.getTeam().getName(), player.getImageUrl(), player.getAge(),
                player.getBirth(), player.getBatInfo(), player.getPitchInfo(), player.getHeight(),
                player.getWeight(), player.getHighSchool(), player.getUniversity(), player.getSalary(),
                player.getFaRemaining(), player.getBackNumber());
    }

    private PlayerDTO(int year, String name, String team, String imageUrl, Long age, LocalDate birth, BatInfo batInfo,
                     PitchInfo pitchInfo, Double height, Double weight, String highSchool,
                     String university, Long salary, Long faRemaining, Long backNumber) {
        this.year = year;
        this.name = name;
        this.team = team;
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
}
