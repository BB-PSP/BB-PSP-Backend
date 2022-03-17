package bbpsp.backend.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PlayerInfoNPitcherAllStatDTO {

    @ApiModelProperty(value = "투수 성적 정보", required = true, example = "{...}")
    @JsonProperty(value = "pitcher_stat")
    private List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList;

    @ApiModelProperty(value = "선수 정보", required = true, example = "{...}")
    @JsonProperty(value = "player_info")
    private PlayerDTO playerDTO;

    public static PlayerInfoNPitcherAllStatDTO createDTO(List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList, PlayerDTO playerDTO) {
        return new PlayerInfoNPitcherAllStatDTO(pitcherStatWithYearDTOList, playerDTO);
    }

    private PlayerInfoNPitcherAllStatDTO(List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList, PlayerDTO playerDTO) {
        this.pitcherStatWithYearDTOList = pitcherStatWithYearDTOList;
        this.playerDTO = playerDTO;
    }
}
