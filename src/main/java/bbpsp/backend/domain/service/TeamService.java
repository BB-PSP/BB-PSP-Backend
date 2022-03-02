package bbpsp.backend.domain.service;

import bbpsp.backend.domain.execption.NoSuchSeasonException;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.domain.persist.Team;
import bbpsp.backend.domain.dto.response.TeamDTO;
import bbpsp.backend.domain.dto.response.TeamListDTO;
import bbpsp.backend.domain.execption.NoSuchTeamException;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.domain.repository.TeamRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;

    public TeamListDTO findAllByYear(int year) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        List<Team> teamList = teamRepository.findAllByYear(season.getId());
        TeamListDTO teamListDTO = new TeamListDTO();
        teamList.forEach(team -> {
            TeamDTO teamDTO = TeamDTO.createTeamDTO(team);
            teamListDTO.addTeamDTO(teamDTO);
        });
        return teamListDTO;
    }

    public Map<String, TeamDTO> findOne(int year, String symbolName) {
        Team team = teamRepository.findOneByYearAndSymbol(year, symbolName)
                .orElseThrow(() -> new NoSuchTeamException(ErrorCode.NO_SUCH_TEAM));
        TeamDTO teamDTO = TeamDTO.createTeamDTO(team);
        Map<String, TeamDTO> map = new ConcurrentHashMap<>();
        map.put(team.getName(), teamDTO);
        return map;
    }
}
