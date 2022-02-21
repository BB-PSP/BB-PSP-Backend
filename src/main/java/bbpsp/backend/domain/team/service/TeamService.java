package bbpsp.backend.domain.team.service;

import bbpsp.backend.domain.player.domain.persist.Season;
import bbpsp.backend.domain.team.domain.persist.Team;
import bbpsp.backend.domain.team.dto.response.TeamDTO;
import bbpsp.backend.domain.team.dto.response.TeamListDTO;
import bbpsp.backend.domain.team.repository.SeasonRepository;
import bbpsp.backend.domain.team.repository.TeamRepository;
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
        Season season = seasonRepository.findByYear(year).orElseThrow();
        List<Team> teamList = teamRepository.findAllByYear(season.getId());
        TeamListDTO teamListDTO = new TeamListDTO();
        teamList.forEach(team -> {
            TeamDTO teamDTO = TeamDTO.createTeamDTO(team);
            teamListDTO.addTeamDTO(teamDTO);
        });
        return teamListDTO;
    }

    public Map<String, TeamDTO> findTeam(int year, String symbolName) {
        Season season = seasonRepository.findByYear(year).orElseThrow();
        Team team = teamRepository.findOneByYearAndSymbol(season.getId(), symbolName).orElseThrow();
        TeamDTO teamDTO = TeamDTO.createTeamDTO(team);
        Map<String, TeamDTO> map = new ConcurrentHashMap<>();
        map.put(team.getName(), teamDTO);
        return map;
    }
}
