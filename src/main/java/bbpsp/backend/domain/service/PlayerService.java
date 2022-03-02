package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.domain.persist.Team;
import bbpsp.backend.domain.dto.response.PlayerDTO;
import bbpsp.backend.domain.dto.response.PlayerListDTO;
import bbpsp.backend.domain.execption.NoSuchPlayerException;
import bbpsp.backend.domain.execption.NoSuchSeasonException;
import bbpsp.backend.domain.execption.NoSuchTeamException;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.domain.repository.TeamRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;

    public PlayerListDTO findPlayerByYearAndName(int year, String name) {
        PlayerListDTO playerListDTO = new PlayerListDTO();
        playerRepository.findAllByName(name)
                .stream()
                .filter(p -> p.getTeam().getSeason().getYear() == year)
                .forEach(p -> playerListDTO.addPlayer(PlayerDTO.createPlayerDTO(p)));
        return playerListDTO;
    }

    public PlayerListDTO findAllByYear(int year) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        PlayerListDTO playerListDTO = new PlayerListDTO();
        playerRepository.findAllByYear(season.getId())
                .forEach(player -> playerListDTO.addPlayer(PlayerDTO.createPlayerDTO(player)));
        return playerListDTO;
    }

    public PlayerListDTO findPlayersAllInfo(String name, LocalDate birth) {
        PlayerListDTO playerListDTO = new PlayerListDTO();
        playerRepository.findByNameAndBirth(name, birth)
                .forEach(p -> playerListDTO.addPlayer(PlayerDTO.createPlayerDTO(p)));
        return playerListDTO;
    }

    public PlayerDTO findPlayerOneYearInfo(int year, String name, LocalDate birth) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findAllByNameAndBirth(name, birth)
                .stream()
                .filter(p -> p.getTeam().getSeason().getId().equals(season.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        return PlayerDTO.createPlayerDTO(player);
    }

    public PlayerListDTO findOneTeamAllPlayers(int year, String symbol) {
        Team team = teamRepository.findOneByYearAndSymbol(year, symbol)
                .orElseThrow(() -> new NoSuchTeamException(ErrorCode.NO_SUCH_TEAM));
        PlayerListDTO playerListDTO = new PlayerListDTO();
        playerRepository.findAllByTeamId(team.getId())
                .forEach(player -> playerListDTO.addPlayer(PlayerDTO.createPlayerDTO(player)));
        return playerListDTO;
    }
}
