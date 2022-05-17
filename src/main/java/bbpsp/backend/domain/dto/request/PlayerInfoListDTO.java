package bbpsp.backend.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayerInfoListDTO {

    @JsonProperty(value = "player_info_list")
    private List<PlayerInfoDTO> playerInfoDTOList;

    public PlayerInfoListDTO() {
        this.playerInfoDTOList = new ArrayList<>();
    }

    public void addPlayerInfo(PlayerInfoDTO playerInfoDTO) {
        this.playerInfoDTOList.add(playerInfoDTO);
    }
}
