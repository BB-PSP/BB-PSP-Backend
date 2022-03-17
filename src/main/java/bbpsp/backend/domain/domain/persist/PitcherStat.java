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

    @Column(name = "_GS")
    private int GS;

    @Column(name = "_CG")
    private int CG;

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

    @Column(name = "_IBB")
    private int IBB;

    @Column(name = "_HBP")
    private int HBP;

    @Column(name = "_BK")
    private int BK;

    @Column(name = "_WP")
    private int WP;

    @Column(name = "_SO")
    private int SO;

    @Column(name = "_R")
    private int R;

    @Column(name = "_ER")
    private int ER;

    @Column(name = "_K_9")
    private double K_9;

    @Column(name = "_BB_9")
    private double BB_9;

    @Column(name = "_K_BB")
    private double K_BB;

    @Column(name = "_H_9")
    private double H_9;

    @Column(name = "_HR_9")
    private double HR_9;

    @Column(name = "_LOB")
    private double LOB;

    @Column(name = "_FIP")
    private double FIP;

    @Column(name = "_BABIP")
    private double BABIP;

    @Column(name = "_WHIP")
    private double WHIP;

    public static PitcherStat createPitcherStat(double ERA, int G, int GS, int CG, int SHO, int W, int L, int SV, int HLD,
                                                double WPCT, int TBF, double IP, int H, int HR, int BB,
                                                int IBB, int HBP, int BK, int WP, int SO, int R,
                                                int ER, double K_9, double BB_9, double K_BB, double H_9,
                                                double HR_9, double LOB, double FIP, double BABIP, double WHIP) {
        return new PitcherStat(
                ERA, G, GS, CG, SHO, W, L, SV, HLD,
                WPCT, TBF, IP, H, HR, BB, IBB, HBP, BK, WP, SO, R,
                ER, K_9, BB_9, K_BB, H_9,
                HR_9, LOB, FIP, BABIP, WHIP
        );
    }

    public void changePitcherStat(PitcherStat pitcherStat) {
        this.ERA = pitcherStat.getERA();
        this.G = pitcherStat.getG();
        this.GS = pitcherStat.getGS();
        this.CG = pitcherStat.getCG();
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
        this.IBB = pitcherStat.getIBB();
        this.HBP = pitcherStat.getHBP();
        this.BK = pitcherStat.getBK();
        this.WP = pitcherStat.getWP();
        this.SO = pitcherStat.getSO();
        this.R = pitcherStat.getR();
        this.ER = pitcherStat.getER();
        this.K_9 = pitcherStat.getK_9();
        this.BB_9 = pitcherStat.getBB_9();
        this.K_BB = pitcherStat.getK_BB();
        this.H_9 = pitcherStat.getH_9();
        this.HR_9 = pitcherStat.getHR_9();
        this.LOB = pitcherStat.getLOB();
        this.FIP =pitcherStat.getFIP();
        this.BABIP = pitcherStat.getBABIP();
        this.WHIP = pitcherStat.getWHIP();
    }

    public PitcherStat(double ERA, int G, int GS, int CG, int SHO, int W, int L, int SV, int HLD,
                       double WPCT, int TBF, double IP, int H, int HR, int BB,
                       int IBB, int HBP, int BK, int WP, int SO, int R,
                       int ER, double K_9, double BB_9, double K_BB, double H_9,
                       double HR_9, double LOB, double FIP, double BABIP, double WHIP) {
        this.ERA = ERA;
        this.G = G;
        this.GS = GS;
        this.CG = CG;
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
        this.IBB = IBB;
        this.HBP = HBP;
        this.BK = BK;
        this.WP = WP;
        this.SO = SO;
        this.R = R;
        this.ER = ER;
        this.K_9 = K_9;
        this.BB_9 = BB_9;
        this.K_BB = K_BB;
        this.H_9 = H_9;
        this.HR_9 = HR_9;
        this.LOB = LOB;
        this.FIP = FIP;
        this.BABIP = BABIP;
        this.WHIP = WHIP;
    }
}
