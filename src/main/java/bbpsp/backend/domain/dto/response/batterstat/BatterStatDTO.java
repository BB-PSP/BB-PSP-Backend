package bbpsp.backend.domain.dto.response.batterstat;

import bbpsp.backend.domain.domain.persist.BatterStat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class BatterStatDTO {


    @ApiModelProperty(value = "경기수", required = true, example = "144")
    private int _G;

    @ApiModelProperty(value = "타율", required = true, example = "0.312")
    private Double _AVG;

    @ApiModelProperty(value = "타석", required = true, example = "312")
    private int _PA;

    @ApiModelProperty(value = "타수", required = true, example = "230")
    private int _AB;

    @ApiModelProperty(value = "득점", required = true, example = "82")
    private int _R;

    @ApiModelProperty(value = "안타", required = true, example = "176")
    private int _H;

    @ApiModelProperty(value = "1루타", required = true, example = "102")
    private int _1B;

    @ApiModelProperty(value = "2루타", required = true, example = "24")
    private int _2B;

    @ApiModelProperty(value = "3루타", required = true, example = "7")
    private int _3B;

    @ApiModelProperty(value = "홈런", required = true, example = "26")
    private int _HR;

    @ApiModelProperty(value = "루타수", required = true, example = "218")
    private int _TB;

    @ApiModelProperty(value = "타점", required = true, example = "87")
    private int _RBI;

    @ApiModelProperty(value = "도루", required = true, example = "16")
    private int _SB;

    @ApiModelProperty(value = "도루자", required = true, example = "4")
    private int _CS;

    @ApiModelProperty(value = "4구", required = true, example = "76")
    private int _BB;

    @ApiModelProperty(value = "고의4구", required = true, example = "3")
    private int _IBB;

    @ApiModelProperty(value = "사구", required = true, example = "4")
    private int _HBP;

    @ApiModelProperty(value = "삼진", required = true, example = "140")
    private int _SO;

    @ApiModelProperty(value = "병살타", required = true, example = "16")
    private int _GDP;

    @ApiModelProperty(value = "장타율", required = true, example = "0.632")
    private Double _SLG;

    @ApiModelProperty(value = "출루율", required = true, example = "0.378")
    private Double _OBP;

    @ApiModelProperty(value = "에러", required = true, example = "3")
    private int _E;

    @ApiModelProperty(value = "희생타", required = true, example = "3")
    private int _SH;

    @ApiModelProperty(value = "희생 플라이", required = true, example = "3")
    private int _SF;



    public static BatterStatDTO createBatterStatDTO(BatterStat b) {
        return new BatterStatDTO(
                b.getG(), b.getAVG(), b.getPA(), b.getAB(), b.getR(),
                b.getH(), b.get_1B(), b.get_2B(), b.get_3B(), b.getHR(), b.getTB(),
                b.getRBI(), b.getSB(), b.getCS(), b.getBB(), b.getIBB(), b.getHBP(),
                b.getSO(), b.getGDP(), b.getSLG(), b.getOBP(), b.getE(), b.getSH(), b.getSF()
        );
    }

    private BatterStatDTO(int G, Double AVG, int PA, int AB, int R,
                         int H, int _1B, int _2B, int _3B, int HR,
                         int TB, int RBI, int SB, int CS, int BB, int IBB, int HBP,
                         int SO, int GDP, Double SLG, Double OBP, int E, int SH, int SF) {
        this._G = G;
        this._AVG = AVG;
        this._PA = PA;
        this._AB = AB;
        this._R = R;
        this._H = H;
        this._1B = _1B;
        this._2B = _2B;
        this._3B = _3B;
        this._HR = HR;
        this._TB = TB;
        this._RBI = RBI;
        this._SB = SB;
        this._CS = CS;
        this._BB = BB;
        this._IBB = IBB;
        this._HBP = HBP;
        this._SO = SO;
        this._GDP = GDP;
        this._SLG = SLG;
        this._OBP = OBP;
        this._E = E;
        this._SF = SF;
        this._SH = SH;
    }
}
