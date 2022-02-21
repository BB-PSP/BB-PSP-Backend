package bbpsp.backend.domain.team.controller;

import bbpsp.backend.domain.team.dto.response.TeamDTO;
import bbpsp.backend.domain.team.dto.response.TeamListDTO;
import bbpsp.backend.domain.team.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/{year}")
    @ApiOperation(value = "특정 시즌 전체 팀 리스트 가져오기", notes = "연도를 이용해서 해당 연도 전체 팀 리스트를 가져오는 API")
    public ResponseEntity<TeamListDTO> teams(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable int year) {
        return new ResponseEntity<>(teamService.findAllByYear(year), HttpStatus.OK);
    }

    @GetMapping("/{year}/{symbolName}")
    @ApiOperation(value = "특정 연도 특정 팀 정보 가져오기", notes = "연도와, 팀 상징이름을 통해 특정 연도 특정 팀 정보를 가져오는 API")
    public ResponseEntity<Map<String, TeamDTO>> team(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable int year,
            @ApiParam(value = "팀 상징(eagles/twins/bears/heroes)", required = true, example = "eagles")
            @PathVariable String symbolName) {
        return new ResponseEntity<>(teamService.findTeam(year, symbolName), HttpStatus.OK);
    }


}
