package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.dto.response.PlayerDTO;
import bbpsp.backend.domain.dto.response.PlayerListDTO;
import bbpsp.backend.domain.execption.NoSuchPlayerException;
import bbpsp.backend.domain.execption.NoSuchSeasonException;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        playerRepository.findAllByYear(season.getId())
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
