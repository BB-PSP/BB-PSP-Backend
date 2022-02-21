package bbpsp.backend.domain.player.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PlayerListDTO {

    @ApiModelProperty(value = "선수 정보 리스트", required = true, example = "[ '선수1': {...}, '선수2': {...} ]")
    private final List<PlayerDTO> playerList;

    public PlayerListDTO() {
        playerList = new ArrayList<>();
    }

    public void addPlayer(PlayerDTO playerDTO) {
        playerList.add(playerDTO);
    }
}
