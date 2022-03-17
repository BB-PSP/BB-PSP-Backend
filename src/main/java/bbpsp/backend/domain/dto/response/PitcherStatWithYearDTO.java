package bbpsp.backend.domain.dto.response;

import bbpsp.backend.domain.domain.persist.PitcherStat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PitcherStatWithYearDTO {

    @ApiModelProperty(value = "연도", required = true, example = "2017")
    private int _YEAR;

    @ApiModelProperty(value = "당시 소속 팀", required = true, example = "Wyverns")
    private String _TEAM;

    @ApiModelProperty(value = "방어율", required = true, example = "3.25")
    private double _ERA;

    @ApiModelProperty(value = "경기수", required = true, example = "25")
    private int _G;

    @ApiModelProperty(value = "선발 경기수", required = true, example = "22")
    private int _GS;

    @ApiModelProperty(value = "완투", required = true, example = "3")
    private int _CG;

    @ApiModelProperty(value = "완봉", required = true, example = "2")
    private int _SHO;

    @ApiModelProperty(value = "승", required = true, example = "14")
    private int _W;

    @ApiModelProperty(value = "패", required = true, example = "6")
    private int _L;

    @ApiModelProperty(value = "세이브", required = true, example = "34")
    private int _SV;

    @ApiModelProperty(value = "홀드", required = true, example = "21")
    private int _HLD;

    @ApiModelProperty(value = "승률", required = true, example = "0.878")
    private double _WPCT;

    @ApiModelProperty(value = "타자수", required = true, example = "234")
    private int _TBF;

    @ApiModelProperty(value = "이닝", required = true, example = "174.2")
    private double _IP;

    @ApiModelProperty(value = "피안타", required = true, example = "144")
    private int _H;

    @ApiModelProperty(value = "피홈런", required = true, example = "23")
    private int _HR;

    @ApiModelProperty(value = "4구", required = true, example = "67")
    private int _BB;

    @ApiModelProperty(value = "고의4구", required = true, example = "2")
    private int _IBB;

    @ApiModelProperty(value = "사구", required = true, example = "7")
    private int _HBP;

    @ApiModelProperty(value = "보크", required = true, example = "3")
    private int _BK;

    @ApiModelProperty(value = "폭투", required = true, example = "7")
    private int _WP;

    @ApiModelProperty(value = "탈삼진", required = true, example = "188")
    private int _SO;

    @ApiModelProperty(value = "실점", required = true, example = "78")
    private int _R;

    @ApiModelProperty(value = "자책점", required = true, example = "69")
    private int _ER;

    @ApiModelProperty(value = "9이닝 당 삼진", required = true, example = "7.3")
    private double _K_9;

    @ApiModelProperty(value = "9이닝 당 볼넷 허용", required = true, example = "2.4")
    private double _BB_9;

    @ApiModelProperty(value = "삼진/볼넷 비율(볼넷 1개당 삼진 개수)", required = true, example = "3.1")
    private double _K_BB;

    @ApiModelProperty(value = "9이닝 당 피안타", required = true, example = "5.4")
    private double _H_9;

    @ApiModelProperty(value = "9이닝 당 홈런 허용", required = true, example = "1.43")
    private double _HR_9;

    @ApiModelProperty(value = "잔루 비율", required = true, example = "0.76")
    private double _LOB;

    @ApiModelProperty(value = "수비 무관 방어율", required = true, example = "3.04")
    private double _FIP;

    @ApiModelProperty(value = "인플레이된 공의 타율", required = true, example = "0.287")
    private double _BABIP;

    @ApiModelProperty(value = "이닝 당 출루 허용", required = true, example = "1.34")
    private double _WHIP;

    public static PitcherStatWithYearDTO createDTO(PitcherStat p, int year, String team) {
        return new PitcherStatWithYearDTO(year, team, p.getERA(), p.getG(), p.getGS(), p.getCG(), p.getSHO(), p.getW(),
                p.getL(), p.getSV(), p.getHLD(), p.getWPCT(),
                p.getTBF(), p.getIP(), p.getH(), p.getHR(),
                p.getBB(), p.getIBB(), p.getHBP(), p.getBK(), p.getWP(),
                p.getSO(), p.getR(), p.getER(), p.getK_9(), p.getBB_9(), p.getK_BB(),
                p.getH_9(), p.getHR_9(), p.getLOB(), p.getFIP(), p.getBABIP(), p.getWHIP());
    }

    private PitcherStatWithYearDTO(int YEAR, String TEAM, double ERA, int G, int GS, int CG, int SHO, int W, int L, int SV, int HLD,
                           double WPCT, int TBF, double IP, int H, int HR, int BB,
                           int IBB, int HBP, int BK, int WP, int SO, int R,
                           int ER, double K_9, double BB_9, double K_BB, double H_9,
                           double HR_9, double LOB, double FIP, double BABIP, double WHIP) {
        this._YEAR = YEAR;
        this._TEAM = TEAM;
        this._ERA = ERA;
        this._G = G;
        this._GS = GS;
        this._CG = CG;
        this._SHO = SHO;
        this._W = W;
        this._L = L;
        this._SV = SV;
        this._HLD = HLD;
        this._WPCT = WPCT;
        this._TBF = TBF;
        this._IP = IP;
        this._H = H;
        this._HR = HR;
        this._BB = BB;
        this._IBB = IBB;
        this._HBP = HBP;
        this._BK = BK;
        this._WP = WP;
        this._SO = SO;
        this._R = R;
        this._ER = ER;
        this._K_9 = K_9;
        this._BB_9 = BB_9;
        this._K_BB = K_BB;
        this._H_9 = H_9;
        this._HR_9 = HR_9;
        this._LOB = LOB;
        this._FIP = FIP;
        this._BABIP = BABIP;
        this._WHIP = WHIP;
    }
}
