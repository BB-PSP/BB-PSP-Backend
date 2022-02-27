package bbpsp.backend.domain.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamListDTO {

    @ApiModelProperty(value = "전체 팀 리스트", required = true, example = "['Hanwha Eagles': {...}, 'LG Twins': {...}, ... ]")
    private final List<TeamDTO> teamDTOList;

    public void addTeamDTO(TeamDTO teamDTO) {
        teamDTOList.add(teamDTO);
    }

    public TeamListDTO() {
        teamDTOList = new ArrayList<>();
    }
}
