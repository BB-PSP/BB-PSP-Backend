package bbpsp.backend.domain.team.dto.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamListDTO {

    private final List<TeamDTO> teamDTOList;

    public void addTeamDTO(TeamDTO teamDTO) {
        teamDTOList.add(teamDTO);
    }

    public TeamListDTO() {
        teamDTOList = new ArrayList<>();
    }
}
