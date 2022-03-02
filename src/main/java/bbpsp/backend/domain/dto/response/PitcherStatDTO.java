package bbpsp.backend.domain.dto.response;

import bbpsp.backend.domain.domain.persist.PitcherStat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PitcherStatDTO {

    @ApiModelProperty(value = "방어율", required = true, example = "3.25")
    private double _ERA;

    @ApiModelProperty(value = "경기수", required = true, example = "25")
    private int _G;

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

    @ApiModelProperty(value = "사구", required = true, example = "7")
    private int _HBP;

    @ApiModelProperty(value = "탈삼진", required = true, example = "188")
    private int _SO;

    @ApiModelProperty(value = "실점", required = true, example = "78")
    private int _R;

    @ApiModelProperty(value = "자책점", required = true, example = "69")
    private int _ER;

    public static PitcherStatDTO createDTO(PitcherStat p) {
        return new PitcherStatDTO(p.getERA(), p.getG(), p.getSHO(), p.getW(),
                p.getL(), p.getSV(), p.getHLD(), p.getWPCT(),
                p.getTBF(), p.getIP(), p.getH(), p.getHR(),
                p.getBB(), p.getHBP(), p.getSO(), p.getR(), p.getER());
    }

    private PitcherStatDTO(double ERA, int G, int SHO, int W, int L, int SV,
                           int HLD, double WPCT, int TBF, double IP, int H, int HR,
                           int BB, int HBP, int SO, int R, int ER) {
        this._ERA = ERA;
        this._G = G;
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
        this._HBP = HBP;
        this._SO = SO;
        this._R = R;
        this._ER = ER;
    }
}
