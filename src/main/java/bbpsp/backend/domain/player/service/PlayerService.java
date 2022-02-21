package bbpsp.backend.domain.player.service;

import bbpsp.backend.domain.player.domain.persist.Player;
import bbpsp.backend.domain.player.dto.response.PlayerDTO;
import bbpsp.backend.domain.player.dto.response.PlayerListDTO;
import bbpsp.backend.domain.player.error.execption.NoSuchPlayerException;
import bbpsp.backend.domain.season.NoSuchSeasonException;
import bbpsp.backend.domain.player.repository.PlayerRepository;
import bbpsp.backend.domain.season.Season;
import bbpsp.backend.domain.season.SeasonRepository;
import bbpsp.backend.domain.team.repository.TeamRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;

    public PlayerDTO findPlayerByYearAndName(int year, String name) {
        Season season = seasonRepository.findByYear(year).orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findAllByName(name)
                .stream()
                .filter(p -> p.getTeam().getSeason().getId().equals(season.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        return PlayerDTO.createPlayerDTO(player);
    }

    public PlayerListDTO findAllByYear(int year) {
        Season season = seasonRepository.findByYear(year).orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        PlayerListDTO playerListDTO = new PlayerListDTO();
        playerRepository.findAllByYearId(season.getId())
                .forEach(player -> playerListDTO.addPlayer(PlayerDTO.createPlayerDTO(player)));
        return playerListDTO;
    }

    public PlayerListDTO findPlayersAllRecord(String name) {
        PlayerListDTO playerListDTO = new PlayerListDTO();
        playerRepository.findAllByName(name)
                .forEach(p -> playerListDTO.addPlayer(PlayerDTO.createPlayerDTO(p)));
        return playerListDTO;
    }
}
