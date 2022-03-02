package bbpsp.backend.domain.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity @Getter
@Table(name = "pticher_stat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PitcherStat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pitcher_stat_id")
    private Long id;

    @Column(name = "_ERA")
    private double ERA;

    @Column(name = "_G")
    private int G;

    @Column(name = "_SHO")
    private int SHO;

    @Column(name = "_W")
    private int W;

    @Column(name = "_L")
    private int L;

    @Column(name = "_SV")
    private int SV;

    @Column(name = "_HLD")
    private int HLD;

    @Column(name = "_WPCT")
    private double WPCT;

    @Column(name = "_TBF")
    private int TBF;

    @Column(name = "_IP")
    private double IP;

    @Column(name = "_H")
    private int H;

    @Column(name = "_HR")
    private int HR;

    @Column(name = "_BB")
    private int BB;

    @Column(name = "_HBP")
    private int HBP;

    @Column(name = "_SO")
    private int SO;

    @Column(name = "_R")
    private int R;

    @Column(name = "_ER")
    private int ER;

    public static PitcherStat createPitcherStat(double ERA, int G, int SHO, int W,
                                                int L, int SV, int HLD, double WPCT,
                                                int TBF, double IP, int H, int HR, int BB, int HBP,
                                                int SO, int R, int ER) {
        return new PitcherStat(
                ERA, G, SHO, W, L, SV, HLD, WPCT,
                TBF, IP, H, HR, BB, HBP, SO, R, ER
        );
    }

    public void changePitcherStat(PitcherStat pitcherStat) {
        this.ERA = pitcherStat.getERA();
        this.G = pitcherStat.getG();
        this.SHO = pitcherStat.getSHO();
        this.W = pitcherStat.getW();
        this.L = pitcherStat.getL();
        this.SV = pitcherStat.getSV();
        this.HLD = pitcherStat.getHLD();
        this.WPCT = pitcherStat.getWPCT();
        this.TBF = pitcherStat.getTBF();
        this.IP = pitcherStat.getIP();
        this.H = pitcherStat.getH();
        this.HR = pitcherStat.getHR();
        this.BB = pitcherStat.getBB();
        this.HBP = pitcherStat.getHBP();
        this.SO = pitcherStat.getSO();
        this.R = pitcherStat.getR();
        this.ER = pitcherStat.getER();
    }

    private PitcherStat(double ERA, int G, int SHO, int W,
                       int L, int SV, int HLD, double WPCT,
                       int TBF, double IP, int H, int HR, int BB, int HBP,
                       int SO, int R, int ER) {
        this.ERA = ERA;
        this.G = G;
        this.SHO = SHO;
        this.W = W;
        this.L = L;
        this.SV = SV;
        this.HLD = HLD;
        this.WPCT = WPCT;
        this.TBF = TBF;
        this.IP = IP;
        this.H = H;
        this.HR = HR;
        this.BB = BB;
        this.HBP = HBP;
        this.SO = SO;
        this.R = R;
        this.ER = ER;
    }
}
