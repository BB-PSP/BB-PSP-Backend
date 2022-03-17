package bbpsp.backend.domain.controller;

import bbpsp.backend.domain.dto.response.PitcherStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.PlayerInfoNPitcherAllStatDTO;
import bbpsp.backend.domain.service.PitcherStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PlayerInfoNPitcherAllStatDTO> findAllOneBatter(
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

}
