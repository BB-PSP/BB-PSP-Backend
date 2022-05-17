package bbpsp.backend.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PlayerInfoListDTO {

    @JsonProperty(value = "player_info_list")
    private List<PlayerInfoDTO> playerInfoDTOList;
}
