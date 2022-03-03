package bbpsp.backend.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class BatterStatNPlayerDTO {

    @ApiModelProperty(value = "타자 성적 정보", required = true, example = "{...}")
    @JsonProperty(value = "batter_stat")
    private BatterStatDTO batterStatDTO;

    @ApiModelProperty(value = "선수 정보", required = true, example = "{...}")
    @JsonProperty(value = "player_info")
    private PlayerDTO playerDTO;

    public static BatterStatNPlayerDTO createDTO(BatterStatDTO batterStatDTO, PlayerDTO playerDTO) {
        return new BatterStatNPlayerDTO(batterStatDTO, playerDTO);
    }

    private BatterStatNPlayerDTO(BatterStatDTO batterStatDTO, PlayerDTO playerDTO) {
        this.batterStatDTO = batterStatDTO;
        this.playerDTO = playerDTO;
    }
}
