package bbpsp.backend.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PredictBatterDTO {

    @ApiModelProperty(value = "선수 이름", required = true, example = "144")
    private String name;

    @ApiModelProperty(value = "나이", required = true, example = "24")
    private int age;

    @ApiModelProperty(value = "예측 경기수", required = true, example = "144")
    private int pG;

    @ApiModelProperty(value = "예측 타율", required = true, example = "0.312")
    private double pAVG;

    @ApiModelProperty(value = "예측 타석", required = true, example = "312")
    private int pPA;

    @ApiModelProperty(value = "예측 타수", required = true, example = "230")
    private int pAB;

    @ApiModelProperty(value = "예측 득점", required = true, example = "82")
    private int pR;

    @ApiModelProperty(value = "예측 안타", required = true, example = "176")
    private int pH;

//    @ApiModelProperty(value = "예측 2루타", required = true, example = "24")
//    private int p2B;
//
//    @ApiModelProperty(value = "예측 3루타", required = true, example = "7")
//    private int p3B;

    @ApiModelProperty(value = "예측 홈런", required = true, example = "26")
    private int pHR;

//    @ApiModelProperty(value = "예측 루타수", required = true, example = "218")
//    private int pTB;

    @ApiModelProperty(value = "예측 타점", required = true, example = "87")
    private int pRBI;

//    @ApiModelProperty(value = "예측 도루", required = true, example = "16")
//    private int pSB;
//
//    @ApiModelProperty(value = "예측 도루자", required = true, example = "4")
//    private int pCS;

    @ApiModelProperty(value = "예측 4구", required = true, example = "76")
    private int pBB;

//    @ApiModelProperty(value = "예측 고의4구", required = true, example = "3")
//    private int pIBB;
//
//    @ApiModelProperty(value = "예측 사구", required = true, example = "4")
//    private int pHBP;

//    @ApiModelProperty(value = "예측 삼진", required = true, example = "140")
//    private int pSO;

//    @ApiModelProperty(value = "예측 병살타", required = true, example = "16")
//    private int pGDP;

    @ApiModelProperty(value = "예측 장타율", required = true, example = "0.632")
    private double pSLG;

    @ApiModelProperty(value = "예측 출루율", required = true, example = "0.378")
    private double pOBP;

//    @ApiModelProperty(value = "예측 에러", required = true, example = "3")
//    private int pE;
//
//    @ApiModelProperty(value = "예측 희생타", required = true, example = "3")
//    private int pSH;
//
//    @ApiModelProperty(value = "예측 희생 플라이", required = true, example = "3")
//    private int pSF;

    public static PredictBatterDTO createDTO(String name, int age, int pG, int pAB, int pPA, double pAVG, int pR, int pRBI,
                                             int pH, int pHR, int pBB, double pOBP, double pSLG) {
        return new PredictBatterDTO(name, age, pG, pAB, pPA, pAVG, pR, pRBI, pH, pHR, pBB, pOBP, pSLG);
    }

    // G, AB, PA, AVG, R, RBI, H, HR, BB, OBP, SLG
    private PredictBatterDTO(String name, int age, int pG, int pAB, int pPA, double pAVG, int pR, int pRBI,
                             int pH, int pHR, int pBB, double pOBP, double pSLG) {
        this.age = age;
        this.name = name;
        this.pG = pG;
        this.pAVG = pAVG;
        this.pPA = pPA;
        this.pAB = pAB;
        this.pR = pR;
        this.pH = pH;
        this.pHR = pHR;
        this.pRBI = pRBI;
        this.pBB = pBB;
        this.pSLG = pSLG;
        this.pOBP = pOBP;
    }
}
