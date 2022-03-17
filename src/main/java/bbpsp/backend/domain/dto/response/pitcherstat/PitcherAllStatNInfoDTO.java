package bbpsp.backend.domain.dto.response.pitcherstat;

import bbpsp.backend.domain.dto.response.PlayerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PitcherAllStatNInfoDTO {

    @ApiModelProperty(value = "투수 성적 정보", required = true, example = "{...}")
    @JsonProperty(value = "pitcher_stat")
    private List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList;

    @ApiModelProperty(value = "선수 정보", required = true, example = "{...}")
    @JsonProperty(value = "player_info")
    private PlayerDTO playerDTO;

    public static PitcherAllStatNInfoDTO createDTO(List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList, PlayerDTO playerDTO) {
        return new PitcherAllStatNInfoDTO(pitcherStatWithYearDTOList, playerDTO);
    }

    private PitcherAllStatNInfoDTO(List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList, PlayerDTO playerDTO) {
        this.pitcherStatWithYearDTOList = pitcherStatWithYearDTOList;
        this.playerDTO = playerDTO;
    }
}
