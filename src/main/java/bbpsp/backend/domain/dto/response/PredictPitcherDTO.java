package bbpsp.backend.domain.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PredictPitcherDTO {

    @ApiModelProperty(value = "선수 이름", required = true, example = "144")
    private String name;

    @ApiModelProperty(value = "나이", required = true, example = "24")
    private int age;

    @ApiModelProperty(value = "예측 경기수", required = true, example = "144")
    private int pG;

    @ApiModelProperty(value = "예측 이닝수", required = true, example = "131.2")
    private double pIP;

    @ApiModelProperty(value = "예측 방어율", required = true, example = "3.43")
    private double pERA;

    @ApiModelProperty(value = "예측 WHIP", required = true, example = "1.21")
    private double pWHIP;

    @ApiModelProperty(value = "예측 승 수", required = true, example = "13")
    private int pW;

    @ApiModelProperty(value = "예측 패배 수", required = true, example = "6")
    private int pL;

    @ApiModelProperty(value = "예측 탈삼진", required = true, example = "154")
    private int pSO;

    @ApiModelProperty(value = "예측 홀드 수", required = true, example = "23")
    private int pHLD;

    @ApiModelProperty(value = "예측 세이브 수", required = true, example = "15")
    private int pS;

    public static PredictPitcherDTO createDTO(String name, int age, int pG, double pIP, double pERA,
                                              double pWHIP, int pW, int pL, int pSO, int pHLD, int pS) {
        return new PredictPitcherDTO(name, age, pG, pIP, pERA, pWHIP, pW, pL, pSO, pHLD, pS);
    }

    private PredictPitcherDTO(String name, int age, int pG, double pIP, double pERA,
                              double pWHIP, int pW, int pL, int pSO, int pHLD, int pS) {
        this.name = name;
        this.age = age;
        this.pG = pG;
        this.pIP = pIP;
        this.pERA = pERA;
        this.pWHIP = pWHIP;
        this.pW = pW;
        this.pL = pL;
        this.pSO = pSO;
        this.pHLD = pHLD;
        this.pS = pS;
    }
}
