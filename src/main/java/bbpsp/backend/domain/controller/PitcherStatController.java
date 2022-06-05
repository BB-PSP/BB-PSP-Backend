package bbpsp.backend.domain.controller;

import bbpsp.backend.domain.dto.request.PlayerRangeDTO;
import bbpsp.backend.domain.dto.response.PlayerListDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherAllStatNInfoDTO;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.service.PitcherStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@RequestMapping("/api/pitchers")
@RequiredArgsConstructor
@Api(tags = "투수 성적 관련 API")
public class PitcherStatController {

    private final PitcherStatService pitcherStatService;

    @GetMapping
    @ApiOperation(value = "특정 년도의 전체 투수 기록 가져오기(페이징)", notes = "특정 년도의 전체 투수 기록(선수 정보 포함)을 페이징으로 가져오는 API")
    public ResponseEntity<List<PitcherStatNPlayerDTO>> findAllPitchersByYear(
            @ApiParam(value = "연도", required = true, example = "2021")
            @RequestParam(value = "year", defaultValue = "2021") int year,
            @ApiParam(value = "페이지 번호", required = true, example = "0")
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "가져올 개수", required = true, example = "100")
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        return new ResponseEntity<>(pitcherStatService.findAllByYear(year, offset, limit), HttpStatus.OK);
    }

    @GetMapping("/stat")
    @ApiOperation(value = "특정 투수의 전체 기록 가져오기", notes = "특정 투수의 전체 기록을 가져오는 API, 해당선수의 생년월일을 활용")
    public ResponseEntity<PitcherAllStatNInfoDTO> findAllOneBatter(
            @ApiParam(value = "선수 이름", required = true, example = "류현진")
            @RequestParam(value = "name") String name,
            @ApiParam(value = "선수 생년월일", required = true, example = "1999-02-08")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(pitcherStatService.findAllWithOnePitcher(name, birth), HttpStatus.OK);
    }

    @GetMapping("/stat/{year}")
    @ApiOperation(value = "특정 년도의 특정 투수 기록 가져오기", notes = "특정 년도의 특정 투수 기록을 연도와 생년월일을 활용해 가져오는 API")
    public ResponseEntity<PitcherStatNPlayerDTO> findOne(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable(value = "year") int year,
            @ApiParam(value = "선수 이름", required = true, example = "이승호")
            @RequestParam(value = "name") String name,
            @ApiParam(value = "선수 생년월일", required = true, example = "1999-02-08")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(pitcherStatService.findOneByBirthAndName(year, name, birth), HttpStatus.OK);
    }

    // 4. 특정 팀의 특정 년도 전체 투수 기록 가져오기
    @GetMapping("/stat/{year}/{symbol}")
    @ApiOperation(value = "특정 팀의 특정 년도 전체 투수 기록 가져오기", notes = "특정 팀의 특정 년도 전체 투수 기록을 가져오는 API")
    public ResponseEntity<List<PitcherStatNPlayerDTO>> findTeamPlayersRecord(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable(value = "year") int year,
            @ApiParam(value = "팀 상징", required = true, example = "Eagles")
            @PathVariable(value = "symbol") String symbol
    ) {
        return new ResponseEntity<>(pitcherStatService.findAllByTeam(year, symbol), HttpStatus.OK);
    }

    // 5. 특정 년도의 RequestBody로 넘어온 조건의 투수들 기록 가져오기
    @GetMapping("/stat/range/{year}")
    @ApiOperation(value = "특정 년도의 RequestBody로 넘어온 조건의 투수들 기록 가져오기", notes = "json으로 넘어온 투수들 기록을 가져오는 API")
    public ResponseEntity<List<PitcherStatNPlayerDTO>> pitcherRange(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "나이 범위 시작", required = true, example = "20,30")
            @RequestParam(value = "age_range") int[] ageRange,
            @ApiParam(value = "팀 배열", required = true, example = "Eagles,Twins")
            @RequestParam(value = "teams") String[] teamArray,
            @ApiParam(value = "선수 생년월일", required = true, example = "100000,300000", defaultValue = "0,10000000")
            @RequestParam(value = "salary_range") int[] salaryRange
    ) {
        PositionInfo[] positionArray = { PositionInfo.P };
        PlayerRangeDTO dto = PlayerRangeDTO.createDTO(ageRange, positionArray, teamArray, salaryRange);
        return new ResponseEntity<>(pitcherStatService.findRightPitcher(dto, year), HttpStatus.OK);
    }

    @GetMapping("/stat/recommend/{year}")
    @ApiOperation(value = "특정 선수에 대한 추천 선수 리스트를 가져오기", notes = "Request Param으로 선수 이름, 생일 정부를 넘겨 추천 선수 리스트를 가져오는 API")
    public ResponseEntity<List<PitcherStatNPlayerDTO>> pitcherRecommend(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "선수 이름", required = true, example = "정은원")
            @RequestParam(value = "name") String name,
            @ApiParam(value = "선수 생일", required = true, example = "1991-01-01")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(pitcherStatService.recommendPitcher(year, name, birth), HttpStatus.OK);
    }

}
