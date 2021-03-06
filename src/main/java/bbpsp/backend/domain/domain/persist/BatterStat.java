package bbpsp.backend.domain.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@Table(name = "batter_stat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatterStat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "batter_stat_id")
    private Long id;

    @Column(name = "_G")
    private int G;

    @Column(name = "_AVG")
    private Double AVG;

    @Column(name = "_PA")
    private int PA;

    @Column(name = "_AB")
    private int AB;

    @Column(name = "_R")
    private int R;

    @Column(name = "_H")
    private int H;

    @Column(name = "_1B")
    private int _1B;

    @Column(name = "_2B")
    private int _2B;

    @Column(name = "_3B")
    private int _3B;

    @Column(name = "_HR")
    private int HR;

    @Column(name = "_TB")
    private int TB;

    @Column(name = "_RBI")
    private int RBI;

    @Column(name = "_SB")
    private int SB;

    @Column(name = "_CS")
    private int CS;

    @Column(name = "_BB")
    private int BB;

    @Column(name = "_IBB")
    private int IBB;

    @Column(name = "_HBP")
    private int HBP;

    @Column(name = "_SO")
    private int SO;

    @Column(name = "_GDP")
    private int GDP;

    @Column(name = "_SLG")
    private Double SLG;

    @Column(name = "_OBP")
    private Double OBP;

    @Column(name = "_E")
    private int E;

    @Column(name = "_SH")
    private int SH;

    @Column(name = "_SF")
    private int SF;




    public static BatterStat createBatterStat(int G, Double AVG, int PA, int AB, int R,
                                              int H, int _1B, int _2B, int _3B, int HR, int TB,
                                              int RBI, int SB, int CS, int BB, int IBB, int HBP,
                                              int SO, int GDP, Double SLG, Double OBP, int E, int SH, int SF) {
        return new BatterStat(
                G, AVG, PA, AB, R, H, _1B, _2B, _3B, HR, TB,
                RBI, SB, CS, BB, IBB, HBP, SO, GDP, SLG, OBP, E, SH, SF
        );
    }

    public void changeStat(BatterStat batterStat) {
        this.G = batterStat.getG();
        this.AVG = batterStat.getAVG();
        this.PA = batterStat.getPA();
        this.AB = batterStat.getAB();
        this.R = batterStat.getR();
        this.H = batterStat.getH();
        this._1B = batterStat.get_1B();
        this._2B = batterStat.get_2B();
        this._3B = batterStat.get_3B();
        this.HR = batterStat.getHR();
        this.TB = batterStat.getTB();
        this.RBI = batterStat.getRBI();
        this.SB = batterStat.getSB();
        this.CS = batterStat.getCS();
        this.BB = batterStat.getBB();
        this.IBB = batterStat.getIBB();
        this.HBP = batterStat.getHBP();
        this.SO = batterStat.getSO();
        this.GDP = batterStat.getGDP();
        this.SLG = batterStat.getSLG();
        this.OBP = batterStat.getOBP();
        this.E = batterStat.getE();
        this.SH = batterStat.getSH();
        this.SF = batterStat.getSF();
    }

    private BatterStat(int G, Double AVG, int PA, int AB, int R,
                      int H, int _1B, int _2B, int _3B, int HR, int TB,
                      int RBI, int SB, int CS, int BB, int IBB, int HBP,
                      int SO, int GDP, Double SLG, Double OBP, int E, int SH, int SF) {
        this.G = G;
        this.AVG = AVG;
        this.PA = PA;
        this.AB = AB;
        this.R = R;
        this.H = H;
        this._1B = _1B;
        this._2B = _2B;
        this._3B = _3B;
        this.HR = HR;
        this.TB = TB;
        this.RBI = RBI;
        this.SB = SB;
        this.CS = CS;
        this.BB = BB;
        this.IBB = IBB;
        this.HBP = HBP;
        this.SO = SO;
        this.GDP = GDP;
        this.SLG = SLG;
        this.OBP = OBP;
        this.E = E;
        this.SF = SF;
        this.SH = SH;
    }
}
