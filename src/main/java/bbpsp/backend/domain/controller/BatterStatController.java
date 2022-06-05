package bbpsp.backend.domain.controller;

import bbpsp.backend.domain.dto.request.PlayerInfoListDTO;
import bbpsp.backend.domain.dto.request.PlayerRangeDTO;
import bbpsp.backend.domain.dto.response.PlayerListDTO;
import bbpsp.backend.domain.dto.response.PredictBatterDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterAllStatNInfoDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatNPlayerDTO;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.service.BatterStatService;
import io.swagger.annotations.*;
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
@RequestMapping("/api/batters")
@RequiredArgsConstructor
@Api(tags = "타자 성적 관련 API")
public class BatterStatController {

    private final BatterStatService batterStatService;

    /**
     * 1. 특정 년도의 타자 전체 기록 가져오기(페이징)
     * 2. 특정 타자(이름을 포함하면 다 나옴)의 전체 기록 가져오기
     * 3. 특정 타자의 특정 년도 기록 가져오기
     * 4. 특정 타자의 예측된 기록(3년치) 가져오기
     * 5. 특정 타자의 예측된 기록(1년치) 가져오기
     * 6. 미래 특정 시즌(3년 이내)의 예측된 타자 전체 기록 가져오기
     */

    // 1. 특정 년도의 타자 전체 기록 가져오기(페이징)
    @GetMapping
    @ApiOperation(value = "특정 년도의 전체 타자 기록 가져오기(페이징)", notes = "특정 년도의 전체 타자 기록(선수 정보 포함)을 페이징으로 가져오는 API")
    public ResponseEntity<List<BatterStatNPlayerDTO>> findAllBattersByYear(
            @ApiParam(value = "연도", required = true, example = "2021")
            @RequestParam(value = "year", defaultValue = "2021") int year,
            @ApiParam(value = "페이지 번호", required = true, example = "0")
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @ApiParam(value = "가져올 개수", required = true, example = "100")
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        return new ResponseEntity<>(batterStatService.findAllByYear(year, offset, limit), HttpStatus.OK);
    }

    // 2. 특정 타자(이름을 포함하면 다 나옴)의 전체 기록 가져오기
    @GetMapping("/stat")
    @ApiOperation(value = "특정 타자의 전체 기록 가져오기", notes = "특정 타자의 전체 기록을 가져오는 API, 해당 선수의 unique_id를 활용")
    public ResponseEntity<BatterAllStatNInfoDTO> findAllOneBatter(
            @ApiParam(value = "선수 이름", required = true, example = "이대호")
            @RequestParam(value = "name") String name,
            @ApiParam(value = "선수 생년월일", required = true, example = "1982-06-21")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(batterStatService.findAllWithOneBatter(name, birth), HttpStatus.OK);
    }

    // 3. 특정 타자의 특정 년도 기록 가져오기
    @GetMapping("/stat/{year}")
    @ApiOperation(value = "특정 년도의 특정 타자 기록 가져오기", notes = "특정 년도의 특정 타자 기록을 연도와 unique_id를 활용해 가져오는 API")
    public ResponseEntity<BatterStatNPlayerDTO> findOne(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable(value = "year") int year,
            @ApiParam(value = "선수 이름", required = true, example = "이대호")
            @RequestParam(value = "name") String name,
            @ApiParam(value = "선수 생년월일", required = true, example = "1982-06-21")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(batterStatService.findOneByUniqueIdAndYear(year, name, birth), HttpStatus.OK);
    }

    // 4. 특정 팀의 특정 년도 전체 타자 기록 가져오기
    @GetMapping("/stat/{year}/{symbol}")
    @ApiOperation(value = "특정 팀의 특정 년도 전체 타자 기록 가져오기", notes = "특정 팀의 특정 년도 전체 타자 기록을 가져오는 API")
    public ResponseEntity<List<BatterStatNPlayerDTO>> findTeamPlayersRecord(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable(value = "year") int year,
            @ApiParam(value = "팀 상징", required = true, example = "Eagles")
            @PathVariable(value = "symbol") String symbol
    ) {
        return new ResponseEntity<>(batterStatService.findAllByTeam(year, symbol), HttpStatus.OK);
    }

    // 5. 특정 년도의 RequestBody로 넘어온 조건의 타자들 기록 가져오기
    @GetMapping("/stat/range/{year}")
    @ApiOperation(value = "특정 년도의 RequestBody로 넘어온 조건의 투수들 기록 가져오기", notes = "Request Parameter로 넘어온 투수들 기록을 가져오는 API")
    public ResponseEntity<List<BatterStatNPlayerDTO>> batterRange(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "나이 범위(범위 시작, 범위 끝)", required = true, example = "20,30")
            @RequestParam(value = "age_range") int[] ageRange,
            @ApiParam(value = "포지션 배열", required = true, example = "SS,2B,C")
            @RequestParam(value = "positions") PositionInfo[] positionArray,
            @ApiParam(value = "팀 배열", required = true, example = "Eagles,Twins")
            @RequestParam(value = "teams") String[] teamArray,
            @ApiParam(value = "연봉 범위(범위 시작, 범위 끝)", required = true, example = "100000,300000", defaultValue = "0,10000000")
            @RequestParam(value = "salary_range") int[] salaryRange) {
        PlayerRangeDTO dto = PlayerRangeDTO.createDTO(ageRange, positionArray, teamArray, salaryRange);
        return new ResponseEntity<>(batterStatService.findRightBatter(dto, year), HttpStatus.OK);
    }

    @GetMapping("/stat/recommend/{year}")
    @ApiOperation(value = "특정 선수에 대한 추천 선수 리스트를 가져오기", notes = "Request Param으로 선수 이름, 생일 정부를 넘겨 추천 선수 리스트를 가져오는 API")
    public ResponseEntity<PlayerListDTO> batterRecommend(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "선수 이름", required = true, example = "정은원")
            @RequestParam(value = "name") String name,
            @ApiParam(value = "선수 생일", required = true, example = "1991-01-01")
            @RequestParam(value = "birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(batterStatService.recommendBatter(year, name, birth), HttpStatus.OK);
    }
}
