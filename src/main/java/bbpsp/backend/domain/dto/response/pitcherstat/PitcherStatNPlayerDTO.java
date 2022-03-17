package bbpsp.backend.domain.dto.response.pitcherstat;

import bbpsp.backend.domain.dto.response.PlayerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PitcherStatNPlayerDTO {

    @ApiModelProperty(value = "투수 성적 정보", required = true, example = "{...}")
    @JsonProperty(value = "pitcher_stat")
    private PitcherStatDTO pitcherStatDTO;

    @ApiModelProperty(value = "선수 정보", required = true, example = "{...}")
    @JsonProperty(value = "player_info")
    private PlayerDTO playerDTO;

    public static PitcherStatNPlayerDTO createDTO(PitcherStatDTO pitcherStatDTO, PlayerDTO playerDTO) {
        return new PitcherStatNPlayerDTO(pitcherStatDTO, playerDTO);
    }

    private PitcherStatNPlayerDTO(PitcherStatDTO pitcherStatDTO, PlayerDTO playerDTO) {
        this.pitcherStatDTO = pitcherStatDTO;
        this.playerDTO = playerDTO;
    }
}
