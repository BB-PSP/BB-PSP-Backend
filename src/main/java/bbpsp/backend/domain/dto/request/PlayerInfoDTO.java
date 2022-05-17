package bbpsp.backend.domain.dto.request;

import bbpsp.backend.domain.enums.PositionInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlayerInfoDTO {


    @ApiModelProperty(value = "이름", required = true, example = "강지진")
    private String name;

    @ApiModelProperty(value = "생년월일", required = true, example = "1993-04-05")
    private LocalDate birth;

    @ApiModelProperty(value = "이름", required = true, example = "144")
    private int age;

    @ApiModelProperty(value = "포지션 정보", required = true, example = "P")
    private PositionInfo positionInfo;

    public static PlayerInfoDTO createDTO(String name, LocalDate birth, int age, PositionInfo positionInfo) {
        return new PlayerInfoDTO(name, birth, age, positionInfo);
    }

    private PlayerInfoDTO(String name, LocalDate birth, int age, PositionInfo positionInfo) {
        this.name = name;
        this.birth = birth;
        this.age = age;
        this.positionInfo = positionInfo;
    }
}
