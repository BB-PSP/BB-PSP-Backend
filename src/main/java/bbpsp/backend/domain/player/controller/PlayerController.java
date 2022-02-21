package bbpsp.backend.domain.player.controller;

import bbpsp.backend.domain.player.dto.response.PlayerDTO;
import bbpsp.backend.domain.player.dto.response.PlayerListDTO;
import bbpsp.backend.domain.player.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    /**
     * 1. 특정 시즌 특정 선수 정보 가져오기(성적 X)
     * 2. 특정 연도 전체 선수 리스트를 가져오기
     * 3. 특정 타자의 특정 년도 기록 가져오기
     * 4. 특정 타자의 예측된 기록(3년치) 가져오기
     * 5. 특정 타자의 예측된 기록(1년치) 가져오기
     * 6. 미래 특정 시즌(3년 이내)의 예측된 타자 전체 기록 가져오기
     */

    private final PlayerService playerService;

    @GetMapping("/{year}")
    @ApiOperation(value = "특정 시즌 특정 선수 정보 가져오기(성적 X)", notes = "연도와 해당 연도의 선수 이름을 통해 선수 정보를 가쟈오는 API, 성적은 안 가져옴")
    public ResponseEntity<PlayerDTO> player(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "선수이름", required = true, example = "?name=강재민")
            @RequestParam("name") String name) {
        return new ResponseEntity<>(playerService.findPlayerByYearAndName(year, name), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "특정 연도 전체 선수 리스트를 가져오기", notes = "특정 연도의 전체 선수 정보를 리스트로 가져오는 API")
    public ResponseEntity<PlayerListDTO> players(
            @ApiParam(value = "연도", required = true, example = "2021")
            @RequestParam("year") int year) {
        return new ResponseEntity<>(playerService.findAllByYear(year), HttpStatus.OK);
    }

    @GetMapping("/player")
    public ResponseEntity<PlayerListDTO> onePlayerAllRecords(
            @ApiParam(value = "이름", required = true, example = "김한화")
            @RequestParam("name") String name) {
        return new ResponseEntity<>(playerService.findPlayersAllRecord(name), HttpStatus.OK);
    }

}
