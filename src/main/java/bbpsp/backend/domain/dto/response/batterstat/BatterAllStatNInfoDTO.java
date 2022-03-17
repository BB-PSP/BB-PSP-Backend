package bbpsp.backend.domain.dto.response.batterstat;

import bbpsp.backend.domain.dto.response.PlayerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class BatterAllStatNInfoDTO {

    @ApiModelProperty(value = "타자 성적 정보", required = true, example = "{...}")
    @JsonProperty(value = "batter_stat")
    private List<BatterStatWithYearDTO> batterStatWithYearDTOList;

    @ApiModelProperty(value = "선수 정보", required = true, example = "{...}")
    @JsonProperty(value = "player_info")
    private PlayerDTO playerDTO;

    public static BatterAllStatNInfoDTO createDTO(List<BatterStatWithYearDTO> batterStatWithYearDTOList, PlayerDTO playerDTO) {
        return new BatterAllStatNInfoDTO(batterStatWithYearDTOList, playerDTO);
    }

    private BatterAllStatNInfoDTO(List<BatterStatWithYearDTO> batterStatWithYearDTOList, PlayerDTO playerDTO) {
        this.batterStatWithYearDTOList = batterStatWithYearDTOList;
        this.playerDTO = playerDTO;
    }
}
