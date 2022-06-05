package bbpsp.backend.domain.controller;

import bbpsp.backend.domain.dto.request.PlayerInfoListDTO;
import bbpsp.backend.domain.dto.request.PlayerRangeDTO;
import bbpsp.backend.domain.dto.response.AVGSampleDTO;
import bbpsp.backend.domain.dto.response.PredictBatterDTO;
import bbpsp.backend.domain.dto.response.PredictPitcherDTO;
import bbpsp.backend.domain.service.PredictionService;
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
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
@Api(tags = "예측 관련 API")
public class PredictionController {

    private final PredictionService predictionService;

    @GetMapping("/batters/{year}")
    @ApiOperation(value = "특정 년도의 전체 타자의 성적을 예측", notes = "연도 값을 주면 해당 연도의 타자들의 내년 성적을 예측해주는 API")
    public ResponseEntity<List<PredictBatterDTO>> allBatters(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year) {
        return new ResponseEntity<>(predictionService.predictAllBatters(year), HttpStatus.OK);
    }

    @GetMapping("/pitchers/{year}")
    @ApiOperation(value = "특정 년도의 전체 투수의 성적을 예측", notes = "연도 값을 주면 해당 연도의 투수들의 내년 성적을 예측해주는 API")
    public ResponseEntity<List<PredictPitcherDTO>> allPitchers(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year) {
        return new ResponseEntity<>(predictionService.predictAllPitchers(year), HttpStatus.OK);
    }

    @GetMapping("/batters/one/{year}")
    @ApiOperation(value = "특정 년도의 특정 타자의 성적을 예측", notes = "특정 타자의 내년 성적을 예측해주는 API")
    public ResponseEntity<PredictBatterDTO> oneBatter(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "이름", required = true, example = "정은원")
            @RequestParam("name") String name,
            @ApiParam(value = "생일", required = true, example = "1999-01-01")
            @RequestParam("birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(predictionService.predictOneBatter(year, name, birth), HttpStatus.OK);
    }

    @GetMapping("/pitchers/one/{year}")
    @ApiOperation(value = "특정 년도의 특정 투수의 성적을 예측", notes = "특정 투수의 내년 성적을 예측해주는 API")
    public ResponseEntity<PredictPitcherDTO> onePitcher(
            @ApiParam(value = "연도", required = true, example = "2021")
            @PathVariable("year") int year,
            @ApiParam(value = "이름", required = true, example = "정은원")
            @RequestParam("name") String name,
            @ApiParam(value = "생일", required = true, example = "1999-01-01")
            @RequestParam("birth") @DateTimeFormat(iso = DATE) LocalDate birth) {
        return new ResponseEntity<>(predictionService.predictOnePitcher(year, name, birth), HttpStatus.OK);
    }
}
