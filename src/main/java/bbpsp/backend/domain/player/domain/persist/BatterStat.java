package bbpsp.backend.domain.player.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter
@Table(name = "batter_stat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BatterStat {

    @Id @GeneratedValue
    @Column(name = "batter_stat_id")
    private Long id;

    private Long G;
    private Double AVG;
    private Long PA;
    private Long AB;
    private Long R;
    private Long H;
    private Long _2B;
    private Long _3B;
    private Long HR;
    private Long TB;
    private Long RBI;
    private Long SB;
    private Long CS;
    private Long BB;
    private Long HBP;
    private Long SO;
    private Long GDP;
    private Double SLG;
    private Double OBP;
    private Long E;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "batter_id")
    private Batter batter;

    public static BatterStat createBatterStat(Long G, Double AVG, Long PA, Long AB, Long R,
                                              Long H, Long _2B, Long _3B, Long HR, Long TB,
                                              Long RBI, Long SB, Long CS, Long BB, Long HBP,
                                              Long SO, Long GDP, Double SLG, Double OBP, Long E, Batter batter) {
        return new BatterStat(
                G, AVG, PA, AB, R, H, _2B, _3B, HR, TB,
                RBI, SB, CS, BB, HBP, SO, GDP, SLG, OBP, E, batter
        );
    }

    public void changeStat(BatterStat batterStat) {
        this.G = batterStat.getG();
        this.AVG = batterStat.getAVG();
        this.PA = batterStat.getPA();
        this.AB = batterStat.getAB();
        this.R = batterStat.getR();
        this.H = batterStat.getH();
        this._2B = batterStat.get_2B();
        this._3B = batterStat.get_3B();
        this.HR = batterStat.getHR();
        this.TB = batterStat.getTB();
        this.RBI = batterStat.getRBI();
        this.SB = batterStat.getSB();
        this.CS = batterStat.getCS();
        this.BB = batterStat.getBB();
        this.HBP = batterStat.getHBP();
        this.SO = batterStat.getSO();
        this.GDP = batterStat.getGDP();
        this.SLG = batterStat.getSLG();
        this.OBP = batterStat.getOBP();
        this.E = batterStat.getE();
        this.batter = batterStat.getBatter();
    }

    private BatterStat(Long G, Double AVG, Long PA, Long AB, Long R,
                      Long H, Long _2B, Long _3B, Long HR, Long TB,
                      Long RBI, Long SB, Long CS, Long BB, Long HBP,
                      Long SO, Long GDP, Double SLG, Double OBP, Long E, Batter batter) {
        this.G = G;
        this.AVG = AVG;
        this.PA = PA;
        this.AB = AB;
        this.R = R;
        this.H = H;
        this._2B = _2B;
        this._3B = _3B;
        this.HR = HR;
        this.TB = TB;
        this.RBI = RBI;
        this.SB = SB;
        this.CS = CS;
        this.BB = BB;
        this.HBP = HBP;
        this.SO = SO;
        this.GDP = GDP;
        this.SLG = SLG;
        this.OBP = OBP;
        this.E = E;
        this.batter = batter;
    }
}
