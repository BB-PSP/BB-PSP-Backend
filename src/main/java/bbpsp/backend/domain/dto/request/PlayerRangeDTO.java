package bbpsp.backend.domain.dto.request;

import bbpsp.backend.domain.enums.PositionInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerRangeDTO {

    public static PlayerRangeDTO createDTO(int[] ageRange, PositionInfo[] positionArray, String[] teamArray, int[] salaryRange) {
        List<PositionInfo> positionList = new ArrayList<>(Arrays.asList(positionArray));
        List<String> teamList = new ArrayList<>(Arrays.asList(teamArray));
        return new PlayerRangeDTO(ageRange[0], ageRange[1], salaryRange[0], salaryRange[1], positionList, teamList);
    }

    private PlayerRangeDTO(int ageStart, int ageEnd, int salaryStart, int salaryEnd, List<PositionInfo> positionList, List<String> teamList) {
        this.ageStart = ageStart;
        this.ageEnd = ageEnd;
        this.salaryStart = salaryStart;
        this.salaryEnd = salaryEnd;
        this.positionList = positionList;
        this.teamList = teamList;
    }

    @ApiModelProperty(value = "나이 범위 시작", required = true, example = "20")
    @JsonProperty(value="age_start")
    private int ageStart;

    @ApiModelProperty(value = "나이 범위 끝", required = true, example = "25")
    @JsonProperty(value="age_end")
    private int ageEnd;

    @ApiModelProperty(value = "나이 범위 시작", required = true, example = "20")
    @JsonProperty(value="salary_start")
    private int salaryStart;

    @ApiModelProperty(value = "나이 범위 끝", required = true, example = "25")
    @JsonProperty(value="salary_end")
    private int salaryEnd;

    @ApiModelProperty(value = "포지션 리스트", required = true, example = "SS, 1B, OF")
    @JsonProperty(value="position_list")
    private List<PositionInfo> positionList;

    @ApiModelProperty(value = "팀", required = true, example = "SS")
    @JsonProperty(value="team_list")
    private List<String> teamList;

}
