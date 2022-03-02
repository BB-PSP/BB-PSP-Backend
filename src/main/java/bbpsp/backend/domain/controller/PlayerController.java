package bbpsp.backend.domain.controller;

import bbpsp.backend.domain.dto.response.PlayerDTO;
import bbpsp.backend.domain.dto.response.PlayerListDTO;
import bbpsp.backend.domain.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    /**
     * 1. 특정 시즌 특정 선수 정보 가져오기(성적 X)
     * 2. 특정 연도 전체 선수 정보 리스트를 가져오기
     * 3.
     */

    private final PlayerService playerService;

    @GetMapping("/{year}")
    @ApiOperation(value = "특정 시즌 특정 선수 정보 가져오기(성적 X)[WARNING]", notes = "연도와 해당 연도의 선수 이름을 통해 선수 정보를 가쟈오는 API, 동명이인이 있을경우 다 포함")
    public ResponseEntity<PlayerListDTO> player(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "선수이름", required = true, example = "?name=강재민")
            @RequestParam("name") String name) {
        return new ResponseEntity<>(playerService.findPlayerByYearAndName(year, name), HttpStatus.OK);
    }

    @GetMapping("/player/{year}")
    @ApiOperation(value = "특정 시즌 특정 선수 정보(생년월일 활용) 가져오기(성적 X)", notes = "연도와 해당 연도의 선수 이름(+생년월일)을 통해 선수 정보를 가쟈오는 API, 성적은 안 가져옴")
    public ResponseEntity<PlayerDTO> onePlayerOneYearRecord(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "선수이름", required = true, example = "강재민")
            @RequestParam("name") String name,
            @ApiParam(value = "선수 생년월일", required = true, example = "1999-02-08")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(playerService.findPlayerOneYearInfo(year, name, birth), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "특정 연도 전체 선수 리스트를 가져오기", notes = "특정 연도의 전체 선수 정보를 리스트로 가져오는 API")
    public ResponseEntity<PlayerListDTO> players(
            @ApiParam(value = "연도", required = true, example = "2021")
            @RequestParam("year") int year) {
        return new ResponseEntity<>(playerService.findAllByYear(year), HttpStatus.OK);
    }

    @GetMapping("/player")
    @ApiOperation(value = "특정 선수의 연도별 정보(기록X) 가져오기", notes = "특정 선수의 이름을 통해 해당 선수의 연도별 모든 정보(등번호 등이 변경될 수도 있으므로)를 가져오는 API")
    public ResponseEntity<PlayerListDTO> onePlayerAllRecords(
            @ApiParam(value = "이름", required = true, example = "김한화")
            @RequestParam("name") String name,
            @ApiParam(value = "선수 생년월일", required = true, example = "1999-02-08")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(playerService.findPlayersAllInfo(name, birth), HttpStatus.OK);
    }

    @GetMapping("/{year}/{symbol}")
    @ApiOperation(value = "특정 팀의 특정 년도 선수단 전체 가져오기(기록 X)", notes = "특정 년도의 특정 팀 선수단 전체 선수정보 리스트로 가져오는 API")
    public ResponseEntity<PlayerListDTO> oneTeamAllPlayers(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "팀 상징", required = true, example = "eagles")
            @PathVariable(value = "symbol") String symbol) {
        return new ResponseEntity<>(playerService.findOneTeamAllPlayers(year, symbol), HttpStatus.OK);
    }

}
